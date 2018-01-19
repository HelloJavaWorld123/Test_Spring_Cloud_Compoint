package com.test.eureka.client.test.enums;

import com.test.eureka.client.test.util.BaseEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ************************
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 16:15
 * Version: V1.0
 * Description:
 * **************************
 */
public enum SexEnum
		implements BaseEnum<SexEnum, Integer>
{
	FALMEL(1, "男"),
	MEMAL(0, "女");

	private Integer code;

	private String value;


	static Map<Integer, SexEnum> map = new HashMap<Integer, SexEnum>();

	static
	{
		for (SexEnum value : SexEnum.values())
		{
			map.put(value.getCode(), value);
		}
	}

	SexEnum(Integer code, String value)
	{
		this.code = code; this.value = value;
	}


	public Integer getCode()
	{
		return code;
	}

	public String getValue()
	{
		return value;
	}

	public static SexEnum getCode(Integer code)
	{
		return map.get(code);
	}

	public static SexEnum getValue(Integer code)
	{
		if (Objects.nonNull(code))
		{
			for (SexEnum value : SexEnum.values())
			{
				if (value.getCode()
						.equals(code))
				{
					return value;
				}
			}
		}

		return null;
	}


}
