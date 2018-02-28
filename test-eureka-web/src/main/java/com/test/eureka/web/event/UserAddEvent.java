package com.test.eureka.web.event;

import com.test.eureka.client.test.dto.MemberInDTO;
import org.springframework.context.ApplicationEvent;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/28  9:52
 * Version: V1.0
 * Description: 事件源 --- 继承 ApplicationEvent接口 ,必须重写 构造函数；
 *
 * ======================
 */
public class UserAddEvent extends ApplicationEvent {


    private MemberInDTO inDTO ;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserAddEvent(Object source,MemberInDTO inDTO) {
        super(source);
        this.inDTO = inDTO ;
    }


    public MemberInDTO getInDTO() {
        return inDTO;
    }

    public void setInDTO(MemberInDTO inDTO) {
        this.inDTO = inDTO;
    }
}
