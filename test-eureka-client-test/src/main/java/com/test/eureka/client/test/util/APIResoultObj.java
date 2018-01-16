package com.test.eureka.client.test.util;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 14:28
 * Version: V1.0
 * Description:统一出参的结果
 * ======================
 */
public class APIResoultObj
{
	private Integer code; //状态码

	private String msg; // 描述信息

	private Object data; //返回的数据

	private Boolean successed = true;

	public static APIResoultObj success()
	{
		return new APIResoultObj(CommonCode.SUCCESS);
	}

	public static APIResoultObj success(Object data)
	{
		return new APIResoultObj(data);
	}

	public static APIResoultObj success(Object data, String msg)
	{
		return new APIResoultObj(data, msg, true);
	}

	public static APIResoultObj parameterError()
	{
		return new APIResoultObj(CommonCode.PARAMTER_CODE, "参数错误");
	}

	public static APIResoultObj parameterError(Integer code, String msg)
	{
		return new APIResoultObj(code, msg, true);
	}

	//定义各种构造函数
	public APIResoultObj()
	{
	}

	public APIResoultObj(String msg, Object data, Boolean successed)
	{
		this.msg = msg; this.data = data; this.successed = successed;
	}

	public APIResoultObj(Object dataString,String  msg, Boolean successed)
	{
		this.msg = msg; this.data = data; this.successed = successed;
	}

	public APIResoultObj(Integer code)
	{
		this.code = code;
	}

	public APIResoultObj(Object data)
	{
		this.data = data;
	}

	public APIResoultObj(Integer code, String msg)
	{
		this.code = code; this.msg = msg;
	}

	public APIResoultObj(Integer code, String msg, Object data)
	{
		this(code, msg); this.data = data;
	}

	public APIResoultObj(Integer code, String msg, Object data, Boolean successed)
	{
		this(code, msg, data); this.successed = successed;
	}

	public APIResoultObj(Integer code, String msg, Boolean successed)
	{
		this.code = code;
		this.msg = msg;
		this.successed = successed;
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

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Boolean getSuccessed()
	{
		return successed;
	}

	public void setSuccessed(Boolean successed)
	{
		this.successed = successed;
	}
}
