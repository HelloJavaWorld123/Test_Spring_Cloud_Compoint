package com.test.eureka.web.client.dao;

import com.test.eureka.client.test.dto.Member;
import com.test.eureka.client.test.dto.MemberInDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ======================
 * Created By User: RXK
 * Date: 2018/1/14
 * Time: 12:35
 * Version: V1.0
 * Description:
 * ======================
 */
@Repository
public interface MemberMapper
{
	Member selectById(String id);

	List<Member> selectAll();

	int addMember(@Param("member") MemberInDTO member);

	int deleteById(String id);

	int update(@Param("member") MemberInDTO member);
}
