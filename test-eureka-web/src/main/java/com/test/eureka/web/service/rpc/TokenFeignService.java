package com.test.eureka.web.service.rpc;

import com.test.eureka.web.config.CustomFeignConfig;
import com.test.eureka.web.service.hystrix.TokenFeignServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import rpc.service.TokenUtilsService;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/3/2  9:04
 * Version: V1.0
 * Description:用于密码进行 加 解 迷的 操作
 * ======***********=========
 */
@FeignClient(value = "test-auth-token",configuration = CustomFeignConfig.class,fallback = TokenFeignServiceHystrix.class)
public interface TokenFeignService extends TokenUtilsService {
}
