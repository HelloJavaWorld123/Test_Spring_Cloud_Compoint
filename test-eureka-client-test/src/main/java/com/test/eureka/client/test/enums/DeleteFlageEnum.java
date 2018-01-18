package com.test.eureka.client.test.enums;


import com.test.eureka.client.test.util.BaseEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 13:41
 * Version: V1.0
 * Description:
 * ======================
 */
public enum DeleteFlageEnum
		implements BaseEnum<DeleteFlageEnum, Integer>
{
	DELETE_YES(0, "正常"),
	DELETE_NO(1, "删除");


	private Integer code;

	private String value;

	static Map<Integer, DeleteFlageEnum> map = new HashMap<>();

	static
	{
		for (DeleteFlageEnum value : DeleteFlageEnum.values())
		{
			map.put(value.getCode(),value);
		}
	}

	public static DeleteFlageEnum getCode(Integer code){
		return map.get(code);
	}

	public Integer getCode()
	{
		return code;
	}

	public String getValue()
	{
		return value;
	}

	DeleteFlageEnum()
	{
	}

	DeleteFlageEnum(Integer code, String value)
	{
		this.code = code; this.value = value;
	}

	public static DeleteFlageEnum getValue(Integer code)
	{
		if (Objects.nonNull(code))
		{
			for (DeleteFlageEnum value : DeleteFlageEnum.values())
			{
				if (value.getCode()
						.equals(code))
				{
					return value;
				}
			}
		} return null;
	}

}
