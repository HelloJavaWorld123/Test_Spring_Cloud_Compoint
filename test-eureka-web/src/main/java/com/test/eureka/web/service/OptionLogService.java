package com.test.eureka.web.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; /**
 * ======***********=========
 * Created By User: RXK
 * Date: 2018/1/30  11:11
 * Version: V1.0
 * Description: 操作日志
 * ======***********=========
 */
public interface OptionLogService
{
    /**
     * 将 操作日志 插入MongoDB中
     * @param request ：请求对象
     * @param response ：响应对象
     * @param handler ：处理方法
     * @param modelAndView ：视图对象
     * @param startTime ：开始时间
     * @param endTime ： 结束时间
     */
    void insertLog(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView, Long startTime, long endTime);
}
