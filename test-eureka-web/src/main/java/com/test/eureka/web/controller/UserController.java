package com.test.eureka.web.controller;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.service.rpc.MemberFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/18
 * Time: 16:10
 * Version: V1.0
 * Description:用户操作
 * ======================
 */
@Api(value = "UserController", description = "测试用户接口")
@RestController
@RequestMapping("/api/menage/user")
public class UserController
{
    @Autowired
    private MemberFeignService memberFeignService;

    @ApiOperation(value = "查找用户详情", response = com.test.eureka.client.test.dto.Member.class)
    @PostMapping("/id")
    public ResponseEntity getUser(@RequestBody MemberInDTO dto)
    {
        if (StringUtils.isEmpty(dto.getId()))
        {
            return ResponseEntity.badRequest()
                    .body("缺少参数");
        }
        Member member = memberFeignService.getById(dto.getId());

        if (Objects.isNull(member))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("参数错误");
        }
        return ResponseEntity.ok(member);
    }

    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody MemberInDTO dto)
    {
        if (StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getAddress()) || Objects.isNull(dto.getAge()) || Objects.isNull(dto.getSex()))
        {
            return ResponseEntity.badRequest()
                    .body("缺少必要的参数");
        }

        int i = 0;
        try
        {
            return memberFeignService.addMem(dto);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
                    .body("新增失败");
        }

    }


    @ApiOperation(value = "更新用户信息")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody MemberInDTO dto)
    {
        if (StringUtils.isEmpty(dto.getId()) || StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getAddress()) || Objects.isNull(dto.getAge()) || Objects.isNull(dto.getSex()))
        {
            return ResponseEntity.badRequest()
                    .body("缺少必要的参数");
        }

        try
        {
            return memberFeignService.updateMem(dto);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.METHOD_FAILURE)
                    .body("更新失败");
        }
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/list")
    public ResponseEntity list()
    {
        List<Member> list = memberFeignService.list();

        if (CollectionUtils.isEmpty(list))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("暂无数据");
        }
        return ResponseEntity.ok(list);
    }
}
