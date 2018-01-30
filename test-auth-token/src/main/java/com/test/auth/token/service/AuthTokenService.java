package com.test.auth.token.service;

import org.springframework.stereotype.Service;

/**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/29  17:35
 * Version: V1.0
 * Description:根据用户的账号 获取当前用户的角色以及权限 产生对应的Token
 * ======***********=========
 */
public interface AuthTokenService
{
    /**
     * 根据账号或者手机号码 获取token
     * @param account ：账号或者 手机号码
     */
    void getToekn(String account);
}
