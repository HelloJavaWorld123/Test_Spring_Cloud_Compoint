package com.test.eureka.web.client.rpc.service;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.client.test.enums.DeleteFlageEnum;
import com.test.eureka.client.test.service.MemberClientService;
import com.test.eureka.web.client.dao.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/14
 * Time: 13:36
 * Version: V1.0
 * Description：client端 暴露 服务  此端 提供基础的服务实现
 * ======================
 */
@RestController
public class MemberServiceImpl
        implements MemberClientService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member getById(String id)
    {
        LOGGER.info("********************查询单个用户的信息*************************");
        return memberMapper.selectById(id);
    }

    @Override
    public List<Member> list()
    {
        LOGGER.info("********************查询所有用户的信息*************************");
        return memberMapper.selectAll();
    }

    @Override
    public ResponseEntity addMem(@RequestBody MemberInDTO member)
    {
        LOGGER.info("********************新增单个用户的信息*************************");
        MemberInDTO user = new MemberInDTO();
        user.setAddress(member.getAddress());
        user.setAge(member.getAge());
        user.setName(member.getName());
        user.setSex(member.getSex());
        user.setDeleteFlag(DeleteFlageEnum.DELETE_NO.getCode());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i = memberMapper.addMember(user);
        if (i != 1)
        {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("新增失败");
        }
        return ResponseEntity.ok("新增成功");
    }

    @Override
    public ResponseEntity deleteMem(String id)
    {
        LOGGER.info("********************删除单个用户的信息*************************");
        memberMapper.deleteById(id);
        return ResponseEntity.ok("删除成功");
    }

    @Override
    public ResponseEntity updateMem(@RequestBody MemberInDTO member)
    {
        LOGGER.info("********************更新单个用户的信息*************************");

        Member user = memberMapper.selectById(member.getId());
        if (Objects.isNull(user))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("参数错误");
        }
        int i = memberMapper.update(member);
        if (i != 1)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("更新失败");
        }
        return ResponseEntity.ok().body("更新成功");
    }

    @Override
    public Member getInfoByUserName(String username) {
        return null;
    }


}
