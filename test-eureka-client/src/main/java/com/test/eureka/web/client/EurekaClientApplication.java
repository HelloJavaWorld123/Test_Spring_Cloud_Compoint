package com.test.eureka.web.client;

import com.test.eureka.web.client.config.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ======================
 * Created By Member: RXK
 * Date: 2018/1/13
 * Time: 15:54
 * Version: V1.0
 * Description:
 * ======================
 */
@SpringBootApplication
@MapperScan(annotationClass = Repository.class, basePackages = {"com.test.eureka.web.client.dao"})
@ComponentScan(basePackages = {"com.test.eureka.web.client"})
@EnableTransactionManagement
//@EnableFeignClients
//@EnableDiscoveryClient
@ServletComponentScan
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Import(DruidConfig.class)
public class EurekaClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaClientApplication.class).web(true).run(args);
    }

}
