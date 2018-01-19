package com.test.eureka.web.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
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
@MapperScan(annotationClass = Repository.class,basePackages = {"com.test.eureka.web.client.dao"})
@ComponentScan(basePackages = {"com.test.eureka"})
@SpringBootApplication
@EnableTransactionManagement
public class EurekaClientApplication
{
	public static void main(String[]args){
		new SpringApplicationBuilder(EurekaClientApplication.class).web(true)
				.run(args);
	}
}
