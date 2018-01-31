package com.test.eureka.web.intercepter;

import com.test.eureka.web.service.OptionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  10:06
 * Version: V1.0
 * Description:操作日志的拦截器
 * 记录的主要的字段有：
 * 开始时间 结束时间 消耗时长 接收的参数 响应的状态 以及 响应的参数 请求的IP 执行的方法
 * ======================
 */
public class OptionLogIntecepter implements HandlerInterceptor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionLogIntecepter.class);

    private ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();

    @Autowired
    private OptionLogService optionLogService;

    /**
     * 在 进入 controller之前 执行该方法
     * @param request ：请求对象
     * @param response ：响应对象
     * @param handler ： 处理的方法
     * @return ： true 接着向下执行拦截器  false 终端执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        LOGGER.info("********************进行 preHandle 方法********************");
        long startTime = System.currentTimeMillis();
        timeThreadLocal.set(startTime);
        LOGGER.info("***************出preHandle*************************");
        return true;
    }

    /**
     * 在返回 ModelAndView之前时  执行该方法
     * @param request ： 请求对象
     * @param response ： 响应对象
     * @param handler ： 对应方法对象
     * @param modelAndView ： 视图对象
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        LOGGER.info("***************进入postHandle*************************");
        long endTime = System.currentTimeMillis();
        optionLogService.insertLog(request,response,handler,modelAndView, timeThreadLocal.get(),endTime);
        LOGGER.info("***************出postHandle*************************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
