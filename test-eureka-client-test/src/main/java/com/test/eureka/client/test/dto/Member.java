package com.test.eureka.client.test.dto;

/**
 * ======================
 * Created By Member: RXK
 * Date: 2018/1/14
 * Time: 12:29
 * Version: V1.0
 * Description:
 * ======================
 */
public class Member
{
	private String name ;

	private Integer age ;

	private String address ;

	private Integer sex ;


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public Integer getSex()
	{
		return sex;
	}

	public void setSex(Integer sex)
	{
		this.sex = sex;
	}
}
