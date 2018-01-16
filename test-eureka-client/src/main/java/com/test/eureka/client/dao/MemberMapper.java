package com.test.eureka.client.dao;

import com.test.eureka.client.test.dto.Member;
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

	void addMember(@Param("member") Member member);

	void deleteById(String id);

	void update(@Param("member") Member member);
}
