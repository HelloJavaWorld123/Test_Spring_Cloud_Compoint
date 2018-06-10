package com.test.eureka.web.client.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Stack;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/6/9  17:27
 * Version: V1.0
 * Description: 切换数据源的 AOP  在 进入Service 层之前 切换数据源
 * 按照包结构 或者 注解 拦截
 * 拦截以后 将本地线程变量 中的内容删除
 * <p>
 * 注解 解释：
 * 切面 @Aspect
 * ======================
 */
@Aspect
@Order(-1)  //确保 该切面执行的顺序比 事务要早
@Component
public class ChangeDataSourceAop {


    private Stack<Boolean> booleanStack = new Stack<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeDataSourceAop.class);

    @Pointcut("execution(* com.test.eureka.web.client.rpc.service.*.*(..)) && @annotation(com.test.eureka.web.client.config.ReadDataSource)")
    public void pointCut() {
    }


    @Before("pointCut()")
    public void beforeSetRedDataSourceType() {
        LOGGER.info("进入设置读数据库的AOP");
        Boolean flag = false;

        if (StringUtils.isEmpty(DataSourceLookUpKeyThreadLocal.getDataSourceLookUpKey())) {
            LOGGER.info("设置当前的线程为 read");
            DataSourceLookUpKeyThreadLocal.setReadDataSourceLookUpKey();

            flag = true;
        }

        booleanStack.push(flag);
    }


    @Before("pointCut()")
    public void beforeSetWriteDataSourceType() {
        LOGGER.info("设置当前线程中的数据库类型为读");
        Boolean flag = false;

        if (StringUtils.isEmpty(DataSourceLookUpKeyThreadLocal.getDataSourceLookUpKey())) {
            LOGGER.info("设置当前的线程为 write");
            DataSourceLookUpKeyThreadLocal.setWriteDataSourceLookUpKey();

            flag = true;
        }

        booleanStack.push(flag);
    }


    @After("pointCut()")
    public void after(){
        if(booleanStack.pop()){
            LOGGER.info("去除当前线程中的 数据库读写的类型");
            DataSourceLookUpKeyThreadLocal.remove();
        }
    }



}
