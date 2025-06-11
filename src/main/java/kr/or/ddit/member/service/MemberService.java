package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {
	public void createMember(MemberVO member);
	
//	readMemberList();
	public MemberVO readMember(String username);
//	modifyMember
//	removeMember
}
