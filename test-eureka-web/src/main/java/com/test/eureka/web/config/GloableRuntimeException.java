package com.test.eureka.web.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/20  10:54
 * Version: V1.0
 * Description: 使用注解 定义全解的异常处理类
 * ======================
 */
@RestControllerAdvice  //其中包括  @InitBinder @ExceptionHandler
public class GloableRuntimeException {

    /**
     * @InitBinder 注解会作用在 所有的@RequestMapping注解的方法上，进行初始化 比如多Model进行初始化
     *
     * @ExceptionHandler 用于捕获指定的异常类 用于自定义的异常 统一处理
     */


    @ExceptionHandler(value = MyRuntimeException.class)
    public ResponseEntity dileException(){
        //捕捉到指定的异常类时 就执行该处的代码逻辑
        return null ;
    }

}
