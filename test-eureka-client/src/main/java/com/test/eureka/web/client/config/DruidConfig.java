package com.test.eureka.web.client.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/9  15:56
 * Version: V1.0
 * Description:
 * ======================
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,SlaveDataSourceProperties.class})
public class DruidConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class);

    private final DataSourceProperties writeProperties;

    private final SlaveDataSourceProperties readProperties;

    @Autowired
    public DruidConfig(DataSourceProperties dataSourceProperties,SlaveDataSourceProperties readProperties) {
        this.writeProperties = dataSourceProperties;
        this.readProperties = readProperties;
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //IP 白名单
        //registrationBean.addInitParameter("allow", "127.0.0.1");
        //黑名单
        //registrationBean.addInitParameter("deny", "192.168.60.76");
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "123456");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        bean.addInitParameter("urlPatterns", "/");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return bean;
    }

    /**
     * 作为主库 也是 唯一一个 写库
     */
    @Bean(name = "dataSource")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(writeProperties.getUrl());
        dataSource.setUsername(writeProperties.getUsername());
        dataSource.setPassword(writeProperties.getPassword());
        dataSource.setDriverClassName(writeProperties.getDriverClassName());

        dataSource.setTestWhileIdle(writeProperties.getTestWhileIdle());
        dataSource.setTestOnReturn(writeProperties.getTestOnReturn());
        dataSource.setTestOnBorrow(writeProperties.getTestOnBorrow());
        dataSource.setMaxActive(writeProperties.getMaxActive());
        dataSource.setMaxWait(writeProperties.getMaxWait());
        dataSource.setMinIdle(writeProperties.getMinIdle());
        dataSource.setInitialSize(writeProperties.getInitialSize());
        dataSource.setValidationQuery(writeProperties.getValidationQuery());

        dataSource.setMaxOpenPreparedStatements(writeProperties.getMaxOpenPreparedStatements());
        dataSource.setTimeBetweenEvictionRunsMillis(writeProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setPoolPreparedStatements(writeProperties.getPoolPreparedStatements());
        dataSource.setTimeBetweenConnectErrorMillis(writeProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setConnectionProperties(writeProperties.getConnectionProperties());

        try {
            dataSource.setFilters(writeProperties.getFilters());

            List<Filter> filterList=new ArrayList<Filter>();
            filterList.add(wallFilter());
            dataSource.setProxyFilters(filterList);

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("设置Druid过滤器时,出现了异常：{}", e.getMessage());
        }
        return dataSource;
    }

    @Bean(name="readDataSource")
    public List<DataSource> readDataSource(){
        List<DataSource> list = new ArrayList<>();
        //不使用的时候 在配置文件中 直接删除掉 也不会报错
        if(readProperties != null && ArrayUtils.isNotEmpty(readProperties.getUrl())) {

            DruidDataSource readDataSource = new DruidDataSource();
            readDataSource.setDriverClassName(readProperties.getDriverClassName());
            try {
                readDataSource.setFilters(readProperties.getFilters());
                List<Filter> filters = new ArrayList<>();
                filters.add(wallFilter());
                dataSource().setProxyFilters(filters);

            } catch (SQLException e) {
                LOGGER.info("读库设置过滤出现异常:{}", e.getMessage());
            }
            readDataSource.setMaxWait(readProperties.getMaxWait());
            readDataSource.setMinIdle(readProperties.getMinIdle());
            readDataSource.setMaxActive(readProperties.getMaxActive());
            readDataSource.setInitialSize(readProperties.getInitialSize());
            readDataSource.setTestOnBorrow(readProperties.getTestOnBorrow());
            readDataSource.setTestOnReturn(readProperties.getTestOnReturn());
            readDataSource.setTestWhileIdle(readProperties.getTestWhileIdle());
            readDataSource.setInitialSize(readProperties.getInitialSize());
            readDataSource.setValidationQuery(readProperties.getValidationQuery());
            readDataSource.setConnectionProperties(readProperties.getConnectionProperties());
            readDataSource.setPoolPreparedStatements(readProperties.getPoolPreparedStatements());
            readDataSource.setMaxOpenPreparedStatements(readProperties.getMaxOpenPreparedStatements());
            readDataSource.setMinEvictableIdleTimeMillis(readProperties.getMinEvictableIdleTimeMillis());
            readDataSource.setTimeBetweenEvictionRunsMillis(readProperties.getTimeBetweenEvictionRunsMillis());
            list.add(readDataSource);
            for(int i =0 ; i< readProperties.getUrl().length; i++){
                DruidDataSource dataSource = readDataSource.cloneDruidDataSource();
                dataSource.setUrl(readProperties.getUrl()[i]);
                dataSource.setUsername(readProperties.getUsername()[i]);
                dataSource.setPassword(readProperties.getPassword()[i]);
                list.add(dataSource);
            }
        }
        return list;
    }


    @Bean
    public WallConfig wallConfig(){
        WallConfig config =new WallConfig();
        config.setMultiStatementAllow(true);//允许一次执行多条语句
        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
        return config;
    }

    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter=new WallFilter();
        wallFilter.setConfig(wallConfig());
        return  wallFilter;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

}
