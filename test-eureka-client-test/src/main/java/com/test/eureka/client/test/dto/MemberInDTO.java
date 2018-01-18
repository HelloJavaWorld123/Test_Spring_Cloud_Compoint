package com.test.eureka.client.test.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 16:50
 * Version: V1.0
 * Description: 用户入参对象
 * ======================
 */
@ApiModel(value = "MemberInDTO",description = "用户操作入参对象")
public class MemberInDTO
{
	@ApiModelProperty(value = "用户Id",name = "id")
	private String id ;

	@ApiModelProperty(value = "用户名称",name = "name")
	private String name ;

	@ApiModelProperty(value = "用户地址",name = "address")
	private String address ;

	@ApiModelProperty(value = "用户年龄",name = "age")
	private Integer age ;

	@ApiModelProperty(value = "用户性别",name = "sex")
	private Integer sex ;


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
}
