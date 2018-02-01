package com.test.eureka.web.controller;

import com.test.eureka.web.dto.LogInInDTO;
import com.test.eureka.web.service.LogInService;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  9:26
 * Version: V1.0
 * Description:用户登录接口
 * ======================
 */
@Controller
public class LogInController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInController.class);


    private LogInService logInService;

    @RequestMapping("/api/manage/login")
    public ResponseEntity logIn(LogInInDTO inDTO)
    {
        LOGGER.info("用户登录");
        try
        {
            UsernamePasswordToken token = new UsernamePasswordToken(inDTO.getAccount(), inDTO.getPassword());

            //TODO
            return logInService.logIn(inDTO);

        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请求失败,请重新请求");
        }
    }


}
