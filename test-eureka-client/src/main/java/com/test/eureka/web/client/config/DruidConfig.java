package com.test.eureka.web.client.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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
@EnableConfigurationProperties({CoustomerDataSource.class})
public class DruidConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfig.class);

    @Autowired
    private CoustomerDataSource properties;

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

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());

        dataSource.setTestWhileIdle(properties.getTestWhileIdle());
        dataSource.setTestOnReturn(properties.getTestOnReturn());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMaxWait(properties.getMaxWait());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setValidationQuery(properties.getValidationQuery());

        dataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        dataSource.setTimeBetweenConnectErrorMillis(properties.getTimeBetweenEvictionRunsMillis());
        dataSource.setConnectionProperties(properties.getConnectionProperties());

        try {
            dataSource.setFilters(properties.getFilters());

            List<Filter> filterList=new ArrayList<Filter>();
            filterList.add(wallFilter());
            dataSource.setProxyFilters(filterList);

        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("设置Druid过滤器时,出现了异常：{}", e.getMessage());
        }
        return dataSource;
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
