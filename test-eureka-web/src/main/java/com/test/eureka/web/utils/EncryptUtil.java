package com.test.eureka.web.utils;

import com.test.eureka.web.dto.LogInInDTO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/1  18:10
 * Version: V1.0
 * Description:使用MD5加盐的方式 对密码进行不可逆的加密
 * ======================
 */
public class EncryptUtil {

    private static final String ALGORRITHM = "md5";

    private static final Integer HASHCOUNT = 1024 ;

    private static final String PRIVATE_SALT = "private_salt";


    public static String encryptPassword(LogInInDTO member){

        //产生随机数作为盐的一部分，但是需要将随机产生的盐保存在数据库中
        SecureRandomNumberGenerator numberGenerator = new SecureRandomNumberGenerator();

        StringBuffer sb = new StringBuffer();
        String salt = sb.append(member.getUserName()).append(numberGenerator.nextBytes().toHex()).toString();

        //经过加密以后的密码  确保用户名的唯一性 就可以保证 即使相同的密码 加密以后的密码也是不一样的
        return new SimpleHash(ALGORRITHM, member.getPassword(), member.getUserName()+PRIVATE_SALT, HASHCOUNT).toHex();
    }


   /* public static void main(String[] args){
        Member member = new Member();
        member.setName("小李");
        member.setPassword("123456");

        String s = encryptPassword(member);

        System.out.println(s);
    }*/



}
