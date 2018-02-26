package com.test.eureka.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/26  16:45
 * Version: V1.0
 * Description: 全局异常处理类
 * 使用实现 ErrorController接口的方法
 * 或者 使用 @ControllerAdvice注解 + @exceptionHandler 注解的形式  -- 比较适合适用于捕捉指定的异常类
 * ======================
 */
@RestController //统一返回json格式的字符串
public class GlobleExceptionController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobleExceptionController.class);

    @Autowired
    private ErrorAttributes errorAttributes;


    /**
     * 根据返回的 地址 找到 对应的方法
     * @return
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseEntity error(HttpServletRequest request) {

        RequestAttributes requestAttributes = new ServletRequestAttributes(request);

        //获取到不同的错误的状态 根据状态的不同返回不同的错误信息
        int status = getStatus(request).value();

        //获取抛出的异常
        Throwable e = errorAttributes.getError(requestAttributes);

        //获取抛出的信息
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(requestAttributes, true);

        String message ;
        switch (status) {
            case 400:
                message = "请求语法错误，服务端无法理解";
                break;
            case 401:
                message = "用户身份缺少认证";
                break;
            case 404:
                message = "无法找到请求的资源，请核实后重新请求";
                break;
            case 500:
                message = "服务器异常，无法找到资源";
                break;
            case 503:
                message = "访问超时";
                break;
            default:
                message = "请重新请求";
        }
        if(null != e ){

           message = message + ":"+ e.getMessage() ;

        }else if(null != errorAttributes.get("message")){

            message = message + ":"+ errorAttributes.get("message");
        }

        return ResponseEntity.ok().body(message);
    }


    /**
     * 获取不同的编码
     *
     * @param request ：请求
     * @return ：httpStatus 状态码
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        //获取到状态码
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (null == status) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        try {
            return HttpStatus.valueOf(status);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
