package com.test.eureka.web.dto;

import java.io.Serializable;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  9:28
 * Version: V1.0
 * Description: 登录入参对象
 * ======================
 */
public class LogInInDTO implements Serializable
{
    private String username ;

    private String password ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
