package com.test.auth.token.service.impl;

import com.test.auth.token.service.AuthTokenService;
import com.test.auth.token.service.rpc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/29  17:38
 * Version: V1.0
 * Description: 产生用户token
 * ======================
 */
@Service
public class AuthTokenServiceImpl implements AuthTokenService
{
    @Autowired
    private UserService userService ;

    @Override
    public void getToekn(String account)
    {

    }
}
