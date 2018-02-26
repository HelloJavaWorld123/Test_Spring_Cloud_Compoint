package com.test.eureka.web.client.rpc.service;

import com.test.eureka.client.test.dto.Resource;
import com.test.eureka.client.test.service.ResourceClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/2/26  14:55
 * Version: V1.0
 * Description:
 * ======================
 */
@Controller
public class ResourceClientServiceImpl implements ResourceClientService {
    @Override
    public ResponseEntity getList() {
        return null;
    }

    @Override
    public ResponseEntity getInfo(String id) {
        return null;
    }

    @Override
    public ResponseEntity add(Resource resource) {
        return null;
    }

    @Override
    public ResponseEntity delete(String id) {
        return null;
    }

    @Override
    public ResponseEntity update(Resource resource) {
        return null;
    }
}
