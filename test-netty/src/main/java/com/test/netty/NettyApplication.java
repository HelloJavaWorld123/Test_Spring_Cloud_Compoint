package com.test.netty;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/28  13:53
 * Version: V1.0
 * Description: 启动配置类
 * ======================
 */
@SpringBootApplication
public class NettyApplication {

    public static void main(String[] args){
        new  SpringApplicationBuilder().sources(NettyApplication.class).web(true).run(args);
    }


}
