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
        BufferedReader br = null;
        InputStream inputStream = null;
        StringBuilder sb = null;
        try
        {
            inputStream = request.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            sb = new StringBuilder();
            String readLine = null;
            while ((readLine = br.readLine()) != null)
            {
                sb.append(readLine);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.info("读取参数时，出现异常:{}", e.getMessage());
        } finally
        {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(inputStream);
        }
        return sb.toString() ;
    }
}
