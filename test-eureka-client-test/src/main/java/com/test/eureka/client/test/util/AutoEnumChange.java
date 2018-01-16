package com.test.eureka.client.test.util;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 13:57
 * Version: V1.0
 * Description: 枚举类与sql自动转换
 * 使用泛型 创建通用的转换工具
 * 在得到查询结果时，在将结果中的数据与枚举的中的值比对转换
 * ======================
 */
public final class AutoEnumChange<T extends BaseEnum<?,?>>
		extends BaseTypeHandler<T>
{
	private Class<T> clz;//枚举类
	private T [] enums; //枚举中的属性

	public AutoEnumChange(Class<T> type)
	{
		if (null == type)
		{
			throw new IllegalArgumentException("%%%%%%%%%%%%%%%枚举类型类不能为空%%%%%%%%%%%%%%%%%%%");
		}

		this.clz = type ;

		this.enums = type.getEnumConstants(); //通过反射获取 枚举中的方法

		if (this.enums == null)
		{
			throw new IllegalArgumentException("***************枚举类型类类型错误**************");
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)
			throws SQLException
	{
		//		ps.setObject(i,(String)parameter.getCode(),jdbcType.TYPE_CODE );
		ps.setObject(i, (String)parameter.getCode(), jdbcType.TYPE_CODE);
	}

	//从返回的结果中 遍历其中的值 用该值再获取到对应的枚举
	@Override
	public T getNullableResult(ResultSet rs, String columnName)
			throws SQLException
	{
		//与数据库中保存的类型一致  如果数据库中保存的类型时String类型的 则使用getString()方法
		Integer i = rs.getInt(columnName);

		if (rs.wasNull())
		{
			return null;
		} else
		{
			//根据获取的数字 获取到枚举类 或者 枚举的某一个值 自定义实现
			return getEnum(i);
		}
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException
	{
		Integer i = rs.getInt(columnIndex);

		if (rs.wasNull())
		{
			return null;
		} else
		{
			return getEnum(i);
		}

	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		Integer i = cs.getInt(columnIndex);

		if (cs.wasNull())
		{
			return null;
		} else
		{
			return getEnum(i);
		}
	}


	private T getEnum(Integer i)
	{
		for (T t : enums)
		{
			if (t.getCode()
					.equals(i))
			{
				return t; //返回对应的枚举中的code 以及 msg
			}
		} throw new IllegalArgumentException("未知的枚举类型");
	}
}
