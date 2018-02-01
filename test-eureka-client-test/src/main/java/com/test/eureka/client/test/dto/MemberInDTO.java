package com.test.eureka.client.test.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 16:50
 * Version: V1.0
 * Description: 用户入参对象
 * ======================
 */
public class MemberInDTO implements Serializable
{
	private String id ;

	private String name ;

	private String address ;

	private Integer age ;

	private Integer sex ;

	private Integer deleteFlag ;

	private Date updateTime;

	private Date createTime ;

	private String updateBy;

	private String createBy;


	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getSex()
	{
		return sex;
	}

	public void setSex(Integer sex)
	{
		this.sex = sex;
	}

	public Integer getDeleteFlag()
	{
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag)
	{
		this.deleteFlag = deleteFlag;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getUpdateBy()
	{
		return updateBy;
	}

	public void setUpdateBy(String updateBy)
	{
		this.updateBy = updateBy;
	}

	public String getCreateBy()
	{
		return createBy;
	}

	public void setCreateBy(String createBy)
	{
		this.createBy = createBy;
	}
}
