package com.test.spring.cloud.eureka.slave;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/11
 * Time: 15:56
 * Version: V1.0
 * Description: 启动类
 * ======================
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSlaveApplication
{
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(EurekaSlaveApplication.class).web(true).run(args);
	}
}
