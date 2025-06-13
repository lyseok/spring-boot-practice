package kr.or.ddit.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Persistent Layer
 */
@Mapper
public interface MemberMapper {
	/**
	 * 한사람의 회원 정보 조회
	 * 
	 * @param username 조회할 회원 pk
	 * @return 조회한 사람의 정보를 가진 VO
	 * 			해당 회원이 존재하지 않는 경우, null을 반환 
	 */
	public MemberVO selectMember(String username);
	/**
	 * oauth2 로그인 사용자의 정보 조회
	 * 
	 * @param username 조회할 회원 pk
	 * @return 조회한 사람의 정보를 가진 VO
	 * 			해당 회원이 존재하지 않는 경우, null을 반환 
	 */
	public MemberVO selectMemberByMail(String mail);
	
	public int insertMember(MemberVO member);
	
	public int updateMemDelete(String username);
	
	public int updateMember(MemberVO member);
	
}
