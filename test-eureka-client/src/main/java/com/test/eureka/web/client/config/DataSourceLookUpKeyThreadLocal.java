package com.test.eureka.web.client.config;

import com.test.eureka.web.client.enums.DatasourceEnum;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/7  22:45
 * Version: V1.0
 * Description: 使用当前线程 保存 使用的数据源的key
 * 一是可以解决并发操作 保证线程安全
 * 二是 读库有多个数据源时  不会随意的跟换
 * ======================
 */
public class DataSourceLookUpKeyThreadLocal {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static void setReadDataSourceLookUpKey(){
        local.set(DatasourceEnum.READ.name());
    }

    public static void setWriteDataSourceLookUpKey(){
        local.set(DatasourceEnum.WRITE.name());
    }


    public static String getDataSourceLookUpKey(){
        return local.get();
    }

    /**
     * 使用完后 必须删除 否则 很可能会操作内存溢出
     */
    public static void remove(){
        local.remove();
    }

}
