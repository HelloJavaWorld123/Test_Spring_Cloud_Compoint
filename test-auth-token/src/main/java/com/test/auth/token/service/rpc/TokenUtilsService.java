package com.test.auth.token.service.rpc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/3/2  9:07
 * Version: V1.0
 * Description: 用于token 远程调用的工具service
 * ======***********=========
 */
public interface TokenUtilsService {

    static final String BASE_URL = "rpc/rsa";

    /**
     * 用户进行登录时 对密码进行解密操作
     * @param password：用户密码
     * @return ：解密以后的密码
     */
    @RequestMapping(value = BASE_URL+"/util",method = RequestMethod.POST)
    String decodePassword(String password);
}
