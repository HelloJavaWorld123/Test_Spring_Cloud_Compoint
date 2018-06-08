package com.test.eureka.web.client.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/6  23:49
 * Version: V1.0
 * Description: 继承 Spring 的AbstractRoutingDataSource
 * 实现 determineCurrentLookupKey  主要是获取当前线程中使用的 是 什么数据源
 * 自己实现该方法  决定实现的方式
 *
 * ======================
 */
public class MyAbstractroutintDataSource extends AbstractRoutingDataSource {

    private int  readDataSourceSize ;


    public MyAbstractroutintDataSource(int readDataSourceSize) {
        this.readDataSourceSize = readDataSourceSize;
    }


    @Override
    protected Object determineCurrentLookupKey() {

       //从当前的线程中 获取 LookUpKey
        String lookUpKey = DataSourceLookUpKeyThreadLocal.getDataSourceLookUpKey();

        //当前环境中 读的


        return null;
    }
}
