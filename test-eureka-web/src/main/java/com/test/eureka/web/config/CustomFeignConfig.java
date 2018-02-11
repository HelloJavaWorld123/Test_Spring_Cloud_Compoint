package com.test.eureka.web.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/10  16:39
 * Version: V1.0
 * Description:修改Feign的部分默认配置
 * 默认配置在 FeignClientsConfiguration 类中，重写其中的bean 并配置中 @FeignClient的注解上
 *
 * 包扫描的类 ：
 * FeignClientsRegistrar
 * ======================
 */
@Configuration
public class CustomFeignConfig {

    /**
     * 默认的配置为 Retryer.NEVER_RETRY;
     * @return 修改成 每间隔 100ms 重试一次，最大的重试时间为 1s 重试次数为 5次
     */
    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }

    /**
     * 设置Fegin的日志输出格式以及级别
     * 默认为 Nooption
     * @return : 级别
     *分为：
     * NONE
     * basic
     * headers
     * Full
     */
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL ;
    }
}
