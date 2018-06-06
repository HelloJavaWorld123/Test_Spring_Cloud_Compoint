package com.test.eureka.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 15:52
 * Version: V1.0
 * Description:
 * ======================
 */
//@EnableFeignClients // 开启feign
//@EnableDiscoveryClient // 开启客户端发现
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@ComponentScan(basePackages = {"com.test.eureka.web"})
@EnableHystrix  //开启熔断  包含有 @EnableCircuitBreaker
//@EnableHystrixDashboard // 开启熔断器的仪表盘
@EnableAsync
public class WebApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(WebApplication.class).web(true).run(args);
	}

}
