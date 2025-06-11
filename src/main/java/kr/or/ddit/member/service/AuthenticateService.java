package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

public interface AuthenticateService {
	/**
	 * 사용자로부터 받은 username과 password로 인증 성공 여부를 판단하기 위한 로직
	 * @param inputData : username 과 password를 가진 객체
	 * @return 인증 성공 / 실패(회원이 없는 경우, 비밀번호 오류의 경우)
	 */
	public boolean authenticate(MemberVO inputData);
}
