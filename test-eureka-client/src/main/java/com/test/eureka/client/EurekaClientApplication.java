package com.test.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
public class EurekaClientApplication
{
	public static void main(String[]args){
		new SpringApplicationBuilder(EurekaClientApplication.class).web(true)
				.run(args);
	}
}
