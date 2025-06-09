package kr.or.ddit.mapper;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Persistent Layer
 */
public interface MemberMapper {
	/**
	 * 한사람의 회원 정보 조회
	 * 
	 * @param username 조회할 회원 pk
	 * @return 조회한 사람의 정보를 가진 VO
	 * 			해당 회원이 존재하지 않는 경우, null을 반환 
	 */
	MemberVO selectMember(String username);
}
