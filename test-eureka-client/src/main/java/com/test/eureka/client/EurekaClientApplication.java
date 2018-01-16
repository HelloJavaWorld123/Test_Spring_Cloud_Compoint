package com.test.eureka.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * ======================
 * Created By Member: RXK
 * Date: 2018/1/13
 * Time: 15:54
 * Version: V1.0
 * Description:
 * ======================
 */
@MapperScan(annotationClass = Repository.class,basePackages = {"com.test.eureka.client"})
@ComponentScan(basePackages = {"com.test.eureka"})
@SpringBootApplication
public class EurekaClientApplication
{
	public static void main(String[]args){
		new SpringApplicationBuilder(EurekaClientApplication.class).web(true)
				.run(args);
	}
}
