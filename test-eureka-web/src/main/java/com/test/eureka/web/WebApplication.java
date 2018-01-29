package com.test.eureka.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 15:52
 * Version: V1.0
 * Description:
 * ======================
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class WebApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(WebApplication.class).web(true).run(args);






	}
}
