package com.test.eureka.web.client.config;

import java.lang.annotation.*;

/**
 * 自定义注解 时 使用的元注解
 * @Retention(RetentionPolicy.RUNTIME) --- 定义 注解的 生命周期 分为三个阶段
 *  1 编译阶段 SOURCE 也称为 源码阶段
 *  2 类加载阶段 CLASS
 *  3 运行阶段 RUNTIME
 *@Target({ElementType.TYPE,ElementType.METHOD})  --- 定义该注解 使用于那些目标上
 *  Type ： 用于Class  Interface Enum 上
 *  Method ：用于方法上
 *  Filed ：用于字段上
 *  Package ：包上
 *  Varlable  LocalVarlable 以及 构造方法上
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ReadDataSource {
}
