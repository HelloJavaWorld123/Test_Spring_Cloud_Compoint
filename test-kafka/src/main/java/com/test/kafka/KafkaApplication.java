package com.test.kafka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/27  16:55
 * Version: V1.0
 * Description:
 * ======================
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[]args){
        new SpringApplicationBuilder(KafkaApplication.class).web(true).run(args);
    }
}
