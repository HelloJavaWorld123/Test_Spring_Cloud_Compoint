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
    private String account ;

    private String password ;

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
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
