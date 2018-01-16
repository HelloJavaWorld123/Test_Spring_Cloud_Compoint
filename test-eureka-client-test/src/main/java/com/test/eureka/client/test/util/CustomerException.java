package com.test.eureka.client.test.util;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 15:25
 * Version: V1.0
 * Description:自定义异常类
 * ======================
 */
public class CustomerException extends Exception
{
	private Integer code ;

	private String msg ;


	public CustomerException(Integer code)
	{
		this.code = code;
	}

	public CustomerException(String message, Integer code)
	{
		super(message);
		this.code = code;
	}

	public CustomerException(String message, Throwable cause, Integer code)
	{
		super(message, cause);
		this.code = code;
	}

	public CustomerException(Throwable cause, Integer code)
	{
		super(cause);
		this.code = code;
	}

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}

	public CustomerException()
	{
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}
