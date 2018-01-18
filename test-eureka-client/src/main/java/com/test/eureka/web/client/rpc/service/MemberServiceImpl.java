package com.test.eureka.web.client.rpc.service;

import com.test.eureka.client.test.dto.MemberInDTO;
import com.test.eureka.web.client.dao.MemberMapper;
import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.service.MemberClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/14
 * Time: 13:36
 * Version: V1.0
 * Description：client端 暴露 服务  此端 提供基础的服务实现
 * ======================
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberClientService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberMapper memberMapper ;

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
	public int addMem(@RequestBody MemberInDTO member)
	{
		LOGGER.info("********************新增单个用户的信息*************************");
		return memberMapper.addMember(member);
	}

	@Override
	public int deleteMem(String id)
	{
		LOGGER.info("********************删除单个用户的信息*************************");
		return memberMapper.deleteById(id);
	}

	@Override
	public int updateMem(@RequestBody MemberInDTO member)
	{
		LOGGER.info("********************更新单个用户的信息*************************");
		return memberMapper.update(member);
	}
}
