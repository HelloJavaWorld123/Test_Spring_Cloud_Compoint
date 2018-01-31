package com.test.eureka.web.config;

import com.test.eureka.web.intercepter.OptionLogIntecepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/31  10:40
 * Version: V1.0
 * Description:
 * ======================
 */
@Configuration
public class CoustomWebMvcConfig extends WebMvcConfigurerAdapter
{

    /**
     * 将自定义的拦截器 添加至 Spring配置中
     *
     * @param registry ： 注册列表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(optionLogIntecepter()).addPathPatterns("/**");
    }

    @Bean
    public OptionLogIntecepter optionLogIntecepter()
    {
        return new OptionLogIntecepter();
    }


}
