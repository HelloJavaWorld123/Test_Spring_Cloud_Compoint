package com.test.eureka.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.test.eureka.web.config.RequestBodyHelper;
import com.test.eureka.web.config.RequestWrapper;
import com.test.eureka.web.dto.OptionLogInDTO;
import com.test.eureka.web.service.OptionLogService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  11:11
 * Version: V1.0
 * Description:
 * ======================
 */
@Service
public class OptionLogServiceImpl implements OptionLogService
{

    private static final Logger logger = LoggerFactory.getLogger(OptionLogServiceImpl.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String MONGO_COLLECTION_NAME = "option_log";

    @Autowired
    private MongoTemplate mongoTemplate ;

    @Override
    public void insertLog(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView, Long startTime, long endTime)
    {


        long consumTime = endTime - startTime; //消耗时长
        String requestStartTime = FORMAT.format(new Date(startTime));
        String requestEndTime = FORMAT.format(new Date(endTime));

        //从请求中获取各项参数
        String requestURI = request.getRequestURI();

        String requestURL = request.getRequestURL().toString();

        String UserAgent = request.getHeader("User-Agent");

        String IP = getRemoteIP(request);

        String body = getRequestBody(request);

        int status = response.getStatus();

        OptionLogInDTO dto = new OptionLogInDTO();

        dto.setConsumeTime(consumTime);
        dto.setStartTime(requestStartTime);
        dto.setEndTime(requestEndTime);
        dto.setRequestURI(requestURI);
        dto.setRequestURL(requestURL);
        dto.setUserAgent(UserAgent);
        dto.setIp(IP);
        dto.setBody(body);
        dto.setStatus(status);

        mongoTemplate.insert(dto,MONGO_COLLECTION_NAME);
        logger.info("*******************插入成功***************");
    }

    /**
     * 获取请求体的 数据
     *
     * @param request ：请求体
     * @return ：json格式的字符串
     */
    private String getRequestBody(HttpServletRequest request)
    {
        //如果是get请求 直接 获取请求参数以及值
        if ("GET".equalsIgnoreCase(request.getMethod()))
        {
            Map<String, String[]> parameterMap = request.getParameterMap();
            return JSONObject.toJSONString(parameterMap);
        }

        return RequestBodyHelper.getBody(request);
    }

    /**
     * 获取远程的IP地址
     *
     * @param request ：请求对象
     * @return ：返回 ip 字符串
     */
    private String getRemoteIP(HttpServletRequest request)
    {

        String ip = request.getHeader("X-Forwarded-For");
        if (logger.isInfoEnabled())
        {
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");
                if (logger.isInfoEnabled())
                {
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (logger.isInfoEnabled())
                {
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (logger.isInfoEnabled())
                {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (logger.isInfoEnabled())
                {
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();
                if (logger.isInfoEnabled())
                {
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
                }
            }
        } else if (ip.length() > 15)
        {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++)
            {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp)))
                {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }
}
