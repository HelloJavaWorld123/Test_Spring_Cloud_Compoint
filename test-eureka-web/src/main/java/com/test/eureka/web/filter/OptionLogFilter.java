package com.test.eureka.web.filter;

import com.test.eureka.web.config.RequestWrapper;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/31  15:03
 * Version: V1.0
 * Description: 利用filter将 入参的request对象 转换成 自己包装后的request对象
 * ======================
 */
@Order(1)
@WebFilter(description = "操作日志过滤器", filterName = "Option_Log", urlPatterns = "/**")
public class OptionLogFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        RequestWrapper requestWrapper = null ;
        if (request instanceof HttpServletRequest)
        {
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper, response);
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {

    }
}
