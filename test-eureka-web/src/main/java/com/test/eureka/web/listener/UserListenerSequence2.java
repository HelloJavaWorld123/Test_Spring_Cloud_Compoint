package com.test.eureka.web.listener;

import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.controller.UserController;
import com.test.eureka.web.event.UserAddEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/28  10:50
 * Version: V1.0
 * Description:
 * ======================
 */
@Component
public class UserListenerSequence2 implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserAddEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        //执行发布事件的来源
        return sourceType == UserController.class;
    }

    @Async //增加异步监听执行
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

            UserAddEvent userAddEvent = (UserAddEvent) event;
            MemberInDTO member = userAddEvent.getInDTO();

            System.out.println("再执行我");
    }

    /**
     * 监听同一个 事件源的 监听器 比其他的监听器返回的数字大或者小 就可以按照顺序执行
     * @return ：int 值 大小
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
