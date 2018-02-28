package com.test.eureka.web.listener;

import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.event.UserAddEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/28  10:01
 * Version: V1.0
 * Description: 事件监听器
 * 监听全局事件或者使用泛型监听指定的事件
 * ======================
 */
@Component
public class UsereventListener implements ApplicationListener{


    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if(event instanceof UserAddEvent){
            UserAddEvent user = (UserAddEvent) event;
            MemberInDTO inDTO = user.getInDTO();
            long timestamp = user.getTimestamp();
            System.out.println("在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss").format(timestamp)+"---"+"新添加的用户名称是："+inDTO.getName()+":"+"性别是："+inDTO.getSex());
        }


    }
}
