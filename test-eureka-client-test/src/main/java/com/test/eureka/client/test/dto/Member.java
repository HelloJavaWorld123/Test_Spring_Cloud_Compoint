package com.test.eureka.client.test.dto;

import com.test.eureka.client.test.enums.DeleteFlageEnum;
import com.test.eureka.client.test.enums.SexEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * ======================
 * Created By Member: RXK
 * Date: 2018/1/14
 * Time: 12:29
 * Version: V1.0
 * Description:
 * ======================
 */
public class Member implements Serializable
{
	private String id ;

	private String name ;

	private String password ;

	private SexEnum sexEnum ;

	private Integer age ;

	private String address ;

	private Integer status ;

	private Date updateTime ;
	private Date createTime ;
	private String createBy;
	private String updateBy;

	private DeleteFlageEnum deleteFlag ;


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

	public SexEnum getSexEnum()
	{
		return sexEnum;
	}

	public void setSexEnum(SexEnum sexEnum)
	{
		this.sexEnum = sexEnum;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
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

	public String getCreateBy()
	{
		return createBy;
	}

	public void setCreateBy(String createBy)
	{
		this.createBy = createBy;
	}

	public String getUpdateBy()
	{
		return updateBy;
	}

	public void setUpdateBy(String updateBy)
	{
		this.updateBy = updateBy;
	}

	public DeleteFlageEnum getDeleteFlag()
	{
		return deleteFlag;
	}

	public void setDeleteFlag(DeleteFlageEnum deleteFlag)
	{
		this.deleteFlag = deleteFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
