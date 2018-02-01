package com.test.eureka.client.test.dto;

import java.io.Serializable;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/1  17:52
 * Version: V1.0
 * Description: 角色实体类
 * ======================
 */
public class Role extends BaseEntity implements Serializable{

    private String name ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
