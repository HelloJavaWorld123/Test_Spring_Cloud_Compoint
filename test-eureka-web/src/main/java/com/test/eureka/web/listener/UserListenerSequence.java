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
 * Date: 2018/2/28  10:36
 * Version: V1.0
 * Description: 第三种 监听器  如果多个监听器同时监听同一个事件源 ，并且要求监听器的逻辑有顺序的执行，
 * 实现 SmartApplicationListener 接口 重写 三种方法；
 * 重要的是 根据 getOrder 返回值的大小 定义 发生的顺序
 * ======================
 */
@Component
public class UserListenerSequence implements SmartApplicationListener{


    /**
     * 当 eventType 时间源 与 指定的 事件源的类 相等时  即 该方法返回true 时，才执行onApplicationEvent 方法
     * @param eventType ：继承了 ApplicationEvent 的事件源
     * @return ：true 或者 false
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserAddEvent.class;
    }

    /**
     * 当 事件源 与 指定的 事件源 相等时 才执行 onApplicationEvent方法
     * @param sourceType ：时间源
     * @return ：true 或者 false
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        //只有在指定的 类来源内 发布的 指定的 时间源 才 有效
        return sourceType == UserController.class;
    }

    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
            UserAddEvent addEvent = (UserAddEvent) event;
            MemberInDTO member = addEvent.getInDTO();

            System.out.println("先执行我");
    }

    /**
     * 多个 监听器 同时 监听 同一个 事件源时，根据此方法返回的int值的大小，按照顺序执行
     * @return : 数字的大小 决定 执行的顺序
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
