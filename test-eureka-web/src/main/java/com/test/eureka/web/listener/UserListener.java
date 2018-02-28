package com.test.eureka.web.listener;

import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.event.UserAddEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/28  10:08
 * Version: V1.0
 * Description:使用注解的方式 实现指定事件的监听
 * ======================
 */
@Component
public class UserListener {

    @Async //增加异步执行
    @EventListener
    public void userEventListener(UserAddEvent event){
        String eventTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss").format(event.getTimestamp());
        //获取事件源
        MemberInDTO member = event.getInDTO();
        System.out.println("在"+eventTime+":"+"新增加一个用户"+member.getName());
    }

}
