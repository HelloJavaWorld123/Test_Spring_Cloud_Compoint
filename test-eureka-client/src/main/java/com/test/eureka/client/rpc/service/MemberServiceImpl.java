package com.test.eureka.client.rpc.service;

import com.test.eureka.client.dao.MemberMapper;
import dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MemberClientService;

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
	@Autowired
	private MemberMapper memberMapper ;

	@Override
	public Member getById(String id)
	{
		return null;
	}

	@Override
	public List<Member> list()
	{
		return null;
	}

	@Override
	public void addMem(Member member)
	{

	}

	@Override
	public void deleteMem(String id)
	{

	}

	@Override
	public void updateMem(Member member)
	{

	}
}
