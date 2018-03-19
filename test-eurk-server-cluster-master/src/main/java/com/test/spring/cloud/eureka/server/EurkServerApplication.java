package com.test.spring.cloud.eureka.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/8
 * Time: 9:55
 * Version: V1.0
 * Description:启动注册服务中心类
 * ======================
 */
@EnableEurekaServer
@SpringBootApplication
public class EurkServerApplication extends SpringBootServletInitializer
{

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EurkServerApplication.class);
	}*/

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(EurkServerApplication.class).web(true).run(args);
	}
}
