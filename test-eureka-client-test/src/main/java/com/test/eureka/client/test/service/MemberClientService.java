package com.test.eureka.client.test.service;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.dto.MemberInDTO;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/14
 * Time: 12:54
 * Version: V1.0
 * Description: client 段对外开放的调用接口
 * ======================
 */
public interface MemberClientService
{
	 static final  String BASE_URL = "/api/user";

	@RequestMapping(value = BASE_URL+"/info",method = RequestMethod.POST)
	Member getById(String id);

	@RequestMapping(value = BASE_URL+"/list",method = RequestMethod.POST)
	List<Member> list();

	@RequestMapping(value = BASE_URL+"/add",method = RequestMethod.POST)
	int addMem(@RequestBody MemberInDTO member);

	@RequestMapping(value = BASE_URL+"/del",method = RequestMethod.POST)
	int deleteMem(String id);

	@RequestMapping(value = BASE_URL+"/update",method = RequestMethod.POST)
	int updateMem(@RequestBody MemberInDTO member);
}
