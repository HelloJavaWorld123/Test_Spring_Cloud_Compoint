package com.test.eureka.web.client.config;

import com.test.eureka.web.client.enums.DatasourceEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/6  23:49
 * Version: V1.0
 * Description: 继承 Spring 的AbstractRoutingDataSource
 * 实现 determineCurrentLookupKey  主要是获取当前线程中使用的 是 什么数据源
 * 自己实现该方法  决定实现的方式
 * <p>
 * ======================
 */
public class MyAbstractroutintDataSource extends AbstractRoutingDataSource {

    private int readDataSourceSize;

    private AtomicInteger count = new AtomicInteger();

    public MyAbstractroutintDataSource(int readDataSourceSize) {
        this.readDataSourceSize = readDataSourceSize;
    }


    @Override
    protected Object determineCurrentLookupKey() {

        //从当前的线程中 获取 LookUpKey
        String lookUpKey = DataSourceLookUpKeyThreadLocal.getDataSourceLookUpKey();

        //当前环境中 读库的数量 不为0 并且 LookUpKey 不为空
        //如果当前线程中 保存的是从库或者读库  数据源会有多个 那个轮询的方式 选择对应的数据源
        if (readDataSourceSize > 0 && StringUtils.isNotBlank(lookUpKey) && lookUpKey.equalsIgnoreCase(DatasourceEnum.READ.name())) {
            int num = count.getAndAdd(1);
            int i = num % readDataSourceSize;
            return new Integer(i);
        }

        // 返回 主库huozhe或者 写的数据源
        return DatasourceEnum.WRITE.name();
    }
}
