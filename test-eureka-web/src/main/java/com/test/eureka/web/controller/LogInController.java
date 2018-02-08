package com.test.eureka.web.controller;

import com.test.eureka.web.dto.LogInInDTO;
import com.test.eureka.web.service.LogInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  9:26
 * Version: V1.0
 * Description:用户登录接口
 * ======================
 */
@Api
@RestController
@RequestMapping("/api/manage")
public class LogInController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInController.class);

    @RequestMapping("/login")
    public ResponseEntity logIn(@RequestBody LogInInDTO inDTO) {
        LOGGER.info("*************************用户登录***************************");


        UsernamePasswordToken token = new UsernamePasswordToken(inDTO.getUsername(), inDTO.getPassword());

        Subject subject = SecurityUtils.getSubject();

        subject.login(token);

        return null;
    }
}
