package com.test.eureka.web.service.rpc;

import com.test.eureka.client.test.service.MemberClientService;
import com.test.eureka.web.config.CustomFeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 17:01
 * Version: V1.0
 * Description: 调用远程的接口
 * ======***********=========
 */
@FeignClient(value = "test-eureka-client",configuration = CustomFeignConfig.class)
public interface MemberFeignService extends MemberClientService
{
}
