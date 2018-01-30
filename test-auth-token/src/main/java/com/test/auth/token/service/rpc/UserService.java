package com.test.auth.token.service.rpc;

import com.test.eureka.client.test.service.MemberClientService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/29  17:54
 * Version: V1.0
 * Description:
 * ======***********=========
 */
@FeignClient(name = "test-eureka-client")
public interface UserService extends MemberClientService
{
}
