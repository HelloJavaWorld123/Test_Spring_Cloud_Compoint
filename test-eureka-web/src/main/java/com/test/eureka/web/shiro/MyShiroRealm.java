package com.test.eureka.web.shiro;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.web.enums.AccountStatusEnum;
import com.test.eureka.web.service.rpc.MemberFeignService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/28  17:59
 * Version: V1.0
 * Description: 继承 AuthorizingRealm 类，自定义实现登录认证以及权限认证
 * ======================
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private MemberFeignService memberFeignService ;



    /**
     * 登录认证
     * @param token ：全局令牌
     * @return ：
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //被加密以后的密码
        char[] password = usernamePasswordToken.getPassword();

        String username = usernamePasswordToken.getUsername();

        //从数据库中根据username查询 相关的信息  提交到 securityManager 安全中心 验证

        Member member = memberFeignService.getInfoByUserName(username);

        //根据 数据库中实际情况 或者 账户被锁定 等异常
        if(null == member){
            throw new UnknownAccountException("账户不存在");
        }

        if(member.getStatus().equals(AccountStatusEnum.LOCK.getCode())){
            throw new LockedAccountException("用户账号状态异常");
        }

        //调用父类的方法，作为info的参数
        String realName = getName();

      return new SimpleAuthenticationInfo(member,member.getPassword(),realName);
    }


    /**
     * 权限认证
     * @param principals ：当前登录的实体
     * @return ：
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {




        return null;
    }

}
