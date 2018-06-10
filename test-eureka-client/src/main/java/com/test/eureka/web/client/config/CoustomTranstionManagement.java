package com.test.eureka.web.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/10  10:47
 * Version: V1.0
 * Description: 自定义 事务管理
 * ======================
 */
@Configuration
@EnableTransactionManagement
public class CoustomTranstionManagement  extends DataSourceTransactionManagerAutoConfiguration {


    @Resource(name = "dataSource")
    private DataSource writerDataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoustomTranstionManagement.class);

    public DataSourceTransactionManager transactionManager(){
        LOGGER.info("使用读数据库时  进事务管理器");
        return new DataSourceTransactionManager(writerDataSource);
    }

}
