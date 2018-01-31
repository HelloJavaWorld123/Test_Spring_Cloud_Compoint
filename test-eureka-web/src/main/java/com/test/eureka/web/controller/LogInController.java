package com.test.eureka.web.controller;

import com.test.eureka.web.dto.LogInInDTO;
import com.test.eureka.web.service.LogInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api/manage")
public class LogInController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInController.class);


    private LogInService logInService;

    @RequestMapping("/login")
    public ResponseEntity logIn(LogInInDTO inDTO)
    {
        LOGGER.info("用户登录");
        try
        {
            return logInService.logIn(inDTO);

        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求失败,请重新请求");
        }
    }


}
