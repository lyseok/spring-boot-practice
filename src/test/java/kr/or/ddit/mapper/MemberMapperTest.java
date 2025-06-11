package kr.or.ddit.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberMapperTest {
	@Autowired
//	MemberMapper mapper;
	MemberService service;
	
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setMemId("testinsert");
		member.setMemPassword("java");
		member.setMemName("회원가입");
//		int res = mapper.insertMember(member);
		service.createMember(member);
		
	}

}
