package com.test.eureka.web.config;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/31  18:13
 * Version: V1.0
 * Description:从request的流中获取到请求的内容 做保存
 * ======================
 */
public class RequestBodyHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBodyHelper.class);

    //从request请求流中获取内容 相当于做备份 是的流可以多次读取
    public static String getBody(HttpServletRequest request)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = request.getInputStream();
            String string = IOUtils.toString(inputStream);
            return string ;
        } catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info("读取参数时，出现异常:{}", e.getMessage());
            return null ;
        }
    }
}
