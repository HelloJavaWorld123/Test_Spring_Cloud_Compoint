package com.test.eureka.web.enums;

import io.swagger.models.auth.In;

/**
 * ************************
 * Created By User: RXK
 * Date: 2018/3/1  17:43
 * Version: V1.0
 * Description:用户账号状态
 * **************************
 */
public enum AccountStatusEnum {
    NORMAL(0,"账号正常"),
    LOCK(1,"账号被锁");

    private Integer code ;

    private String msg ;

    AccountStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static AccountStatusEnum getValues(Integer code){
        if(null != code){
            for(AccountStatusEnum value : AccountStatusEnum.values()){
                if(value.code.equals(code)){
                    return value ;
                }
            }
        }
        return null ;
    }
}
