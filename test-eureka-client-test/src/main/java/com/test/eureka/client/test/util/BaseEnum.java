package com.test.eureka.client.test.util;

/**
 * ************************
 * Created By User: RXK
 * Date: 2018/1/16
 * Time: 14:00
 * Version: V1.0
 * Description:定义枚举接口 一是使用泛型通用转换 而是所有的枚举集成该接口 规范接口的形式
 * **************************
 */
public interface BaseEnum<T extends Enum<?>,E>
{
	//定义最基础的 get方法 在子类中实现即可  或者其他的 基础的方法
	E getCode();
	String getValue();

}
