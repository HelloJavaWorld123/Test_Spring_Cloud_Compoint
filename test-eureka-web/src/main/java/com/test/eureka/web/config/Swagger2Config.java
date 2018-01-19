package com.test.eureka.web.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 21:03
 * Version: V1.0
 * Description: Swagger2的配置文件
 * ======================
 */
@Configuration
@EnableSwagger2
public class Swagger2Config
{
	@Bean
	public Docket webDocket()
	{
		List<Parameter> parameters = new ArrayList<>();
		ParameterBuilder builder = new ParameterBuilder();
		builder.defaultValue("token")  //输入框中显示的值，类似于占位符；
				.description("令牌")   //参数描述
				.modelRef(new ModelRef("String")) //对应参数数据类型
				.parameterType("header")  // header, cookie, body, query
				.name("access-token") //参数入参名称
				.required(false)   //是否必传
				.build();
		Parameter parameter = builder.build();
		parameters.add(parameter);
		return new Docket(DocumentationType.SWAGGER_2).enable(true)
				.groupName("web_api")  //组名称
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.apis(Predicates.or(RequestHandlerSelectors.basePackage("com.test.eureka.web.controller"))) //扫描的包
				.paths(PathSelectors.regex("/api/menage/user.*"))  //过滤的接口
				.build()
				.globalOperationParameters(parameters) //公共参数
				.apiInfo(webApiInfo());  //所使用的文档基础信息
	}


	private ApiInfo webApiInfo()
	{
		Contact contact = new Contact("RXK", "localhost:9666", "http://127.0.0.1:9999");

		return new ApiInfoBuilder().description("Web端接口")
				.title("API")
				.contact(contact)
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/")
				.version("V1.0")
				.build();
	}
}
