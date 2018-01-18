package com.test.eureka.web.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
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

	static List<Parameter> list = new ArrayList<>();

	//初始化所有接口的 必须传入的所有的参数；
	static
	{
		ParameterBuilder parameterBuilder = new ParameterBuilder();

		parameterBuilder.defaultValue("token")
				.description("令牌")
				.modelRef(new ModelRef("String"))
				.parameterType("String")
				.required(false)
				.build();

		Parameter build = parameterBuilder.build();
		list.add(build);
	}


	@Bean
	public Docket webDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2).enable(true)
				.groupName("web_service_api")  //显示在小方框中的名称
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.eureka.web")) //过滤包
				.paths(Predicates.or(PathSelectors.regex("/api/user/.*"))) //过滤接口
				.build()
				.globalOperationParameters(list)  //为所有的接口 统一添加需要传入的参数 比如 token 之类的参数 必须传入的
				.apiInfo(webApiInfo());
	}


	public ApiInfo webApiInfo()
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
