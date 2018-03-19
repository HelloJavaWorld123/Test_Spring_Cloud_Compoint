package com.test.eureka.web.service.hystrix;

import com.test.eureka.web.service.rpc.TokenFeignService;
import org.springframework.stereotype.Component;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/3/2  9:11
 * Version: V1.0
 * Description:token 熔断器
 * ======***********=========
 */
@Component
public class TokenFeignServiceHystrix implements TokenFeignService{
    @Override
    public String decodePassword(String password) {
        return "服务器异常";
    }
}
