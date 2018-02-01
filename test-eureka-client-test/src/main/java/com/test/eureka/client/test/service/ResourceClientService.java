package com.test.eureka.client.test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/1  17:43
 * Version: V1.0
 * Description: 资源
 * ======================
 */
@RestController
public interface ResourceClientService
{

    static final String RESOURCE_RPC = "rpc/resource";


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
