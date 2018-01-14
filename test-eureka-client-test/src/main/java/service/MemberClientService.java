package service;

import dto.Member;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public interface MemberClientService
{
	 static final  String BASE_URL = "/rpc/user";

	@RequestMapping("/info")
	 Member getById(String id);

	@RequestMapping("/list")
	List<Member> list();

	@RequestMapping("/add")
	void addMem(Member member);

	@RequestMapping("/del")
	void deleteMem(String id);

	@RequestMapping("/update")
	void updateMem(Member member);
}
