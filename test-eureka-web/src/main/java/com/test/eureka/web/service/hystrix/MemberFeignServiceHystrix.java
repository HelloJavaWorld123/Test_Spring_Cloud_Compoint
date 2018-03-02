package com.test.eureka.web.service.hystrix;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.service.rpc.MemberFeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/11  9:46
 * Version: V1.0
 * Description: 对于Feign的远程调用的 服务 都提供熔断
 * ======================
 */
@Component
public class MemberFeignServiceHystrix implements MemberFeignService {
    @Override
    public Member getById(String id) {
        return null;
    }

    @Override
    public List<Member> list() {
        return null;
    }

    @Override
    public ResponseEntity addMem(MemberInDTO member) {
        return null;
    }

    @Override
    public ResponseEntity deleteMem(String id) {
        return null;
    }

    @Override
    public ResponseEntity updateMem(MemberInDTO member) {
        return null;
    }

    @Override
    public Member getInfoByUserName(String username) {
        return null;
    }
}
