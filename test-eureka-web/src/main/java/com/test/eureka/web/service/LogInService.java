package com.test.eureka.web.service;

import com.test.eureka.web.dto.LogInInDTO;
import org.springframework.http.ResponseEntity;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/30  9:58
 * Version: V1.0
 * Description:
 * ======***********=========
 */
public interface LogInService
{
    /**
     * 登录验证
     * @param inDTO ： 登录入参
     * @return ： 返回token
     */
    ResponseEntity logIn(LogInInDTO inDTO);
}
