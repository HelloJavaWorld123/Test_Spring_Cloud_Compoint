package com.test.eureka.web.client.config;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/6  22:31
 * Version: V1.0
 * Description:
 * ======================
 */
@Configuration
public class MyBatisConfigration extends MybatisAutoConfiguration {


    @Autowired
    private MybatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader;


    @Resource(name = "dataSource")
    private DataSource writeDataSource;

    @Resource(name = "readDataSource")
    private List<DataSource> readDataSource;

    public MyBatisConfigration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }


    /**
     * 自定义 SqlSessionFactory 中使用的数据源
     *
     * @return ：sqlSessionFactory 对象
     */
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(this.routingDataSource());
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        org.apache.ibatis.session.Configuration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new org.apache.ibatis.session.Configuration();
        }

        factory.setConfiguration(configuration);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory.getObject();
    }


    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        int size = 0;

        if (CollectionUtils.isNotEmpty(readDataSource)) {
            size = readDataSource.size();
        }

        MyAbstractroutintDataSource dataSourceProxy = new MyAbstractroutintDataSource(size);

        Map<Object, Object> targetDataSource = new HashMap<>();

        for (int i = 0; i < readDataSource.size(); i++) {
            targetDataSource.put(i, readDataSource.get(i));
        }

        // 默认的  写库
        dataSourceProxy.setDefaultTargetDataSource(writeDataSource);
        dataSourceProxy.setTargetDataSources(targetDataSource);
        return dataSourceProxy;
    }
}
