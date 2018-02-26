package com.test.eureka.web.service.rpc;

import com.test.eureka.client.test.service.MemberClientService;
import com.test.eureka.web.config.CustomFeignConfig;
import com.test.eureka.web.service.hystrix.MemberFeignServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 17:01
 * Version: V1.0
 * Description: 调用远程的接口
 * fallback 用于在调用服务失败时，feign会开启熔断，调用指定的fallback
 * 防止雪崩效应
 * ======***********=========
 */
@FeignClient(value = "test-eureka-client",configuration = CustomFeignConfig.class,fallback = MemberFeignServiceHystrix.class)
public interface MemberFeignService extends MemberClientService
{
}
