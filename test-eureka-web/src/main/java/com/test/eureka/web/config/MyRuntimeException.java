package com.test.eureka.web.config;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/3/20  10:50
 * Version: V1.0
 * Description: 将运行时异常 换成自定义的异常
 * ======================
 */
public class MyRuntimeException extends RuntimeException{

    private Integer code ;

    private String msg ;

    public MyRuntimeException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyRuntimeException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public MyRuntimeException(String message, Throwable cause, Integer code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public MyRuntimeException(Throwable cause, Integer code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
