package com.test.eureka.web.controller;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.web.dto.LogInInDTO;
import com.test.eureka.web.service.rpc.TokenFeignService;
import com.test.eureka.web.utils.EncryptUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private TokenFeignService tokenFeignService ;

    @RequestMapping("/login")
    public ResponseEntity logIn(@RequestBody LogInInDTO inDTO) {
        LOGGER.info("*************************用户登录***************************");

        //密码 需要使用 私匙进行解密  在经过 加密 传进去
        String password = tokenFeignService.decodePassword(inDTO.getPassword());
        inDTO.setPassword(password);

        String encryptPassword = EncryptUtil.encryptPassword(inDTO);

        UsernamePasswordToken token = new UsernamePasswordToken(inDTO.getUsername(), encryptPassword);

        try {

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

        } catch (AuthenticationException e) {
            e.printStackTrace();
            LOGGER.info("登录授权失败异常",e.getMessage());
        }
        return ResponseEntity.ok("登录成功");
    }

   /* @RequestMapping("/logout")
    public ResponseEntity logout(){

    }*/

}
