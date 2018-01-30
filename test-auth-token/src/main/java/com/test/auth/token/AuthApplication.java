package com.test.auth.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/29  17:31
 * Version: V1.0
 * Description:
 * ======================
 */
@MapperScan(basePackages = {"com.test.auth.token.dao"})
@SpringBootApplication
public class AuthApplication
{
    public static void main(String[] args){
        new SpringApplicationBuilder(AuthApplication.class).web(true).run(args);
    }

}
