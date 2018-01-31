package com.test.eureka.web.dto;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/30  14:53
 * Version: V1.0
 * Description: 入参
 * ======================
 */
public class OptionLogInDTO
{

    private String startTime ;

    private String endTime ;

    private Long consumeTime ;

    private String requestURI ;

    private String requestURL ;

    private String body ;

    private String ip ;

    private String userAgent ;

    private Integer status;

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public Long getConsumeTime()
    {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime)
    {
        this.consumeTime = consumeTime;
    }

    public String getRequestURI()
    {
        return requestURI;
    }

    public void setRequestURI(String requestURI)
    {
        this.requestURI = requestURI;
    }

    public String getRequestURL()
    {
        return requestURL;
    }

    public void setRequestURL(String requestURL)
    {
        this.requestURL = requestURL;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
