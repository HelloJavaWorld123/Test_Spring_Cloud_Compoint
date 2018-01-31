package com.test.eureka.web.config;

import org.apache.commons.io.IOUtils;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/31  10:54
 * Version: V1.0
 * Description: 使用 装饰器 包装request ，获取request.inPutStream，使得流可以读取多次
 * ======================
 */
public class RequestWrapper extends HttpServletRequestWrapper
{

    private byte[] body;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public RequestWrapper(HttpServletRequest request)
    {
        //从request的流中获取到 body ；
        super(request);
        this.body = RequestBodyHelper.getBody(request).getBytes(Charset.forName("UTF-8"));
    }


    @Override
    public BufferedReader getReader()
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream()
    {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream()
        {
            @Override
            public boolean isFinished()
            {
                return false;
            }

            @Override
            public boolean isReady()
            {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener)
            {

            }

            @Override
            public int read() throws IOException
            {
                return byteArrayInputStream.read();
            }
        };

    }

}
