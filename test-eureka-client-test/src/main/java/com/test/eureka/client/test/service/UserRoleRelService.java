package com.test.eureka.client.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/1  17:44
 * Version: V1.0
 * Description:用户----角色
 * ======================
 */
public interface UserRoleRelService {
    static final String RESOURCE_RPC = "rpc/user/role";


    @RequestMapping("/list")
    public ResponseEntity getList();

    @RequestMapping("/id")
    public ResponseEntity getInfo();

    @RequestMapping("/add")
    public ResponseEntity add();

    @RequestMapping("/del")
    public ResponseEntity delete();

    @RequestMapping("/update")
    public ResponseEntity update();

}
