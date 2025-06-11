package kr.or.ddit.filter.auth;

import java.security.Principal;

import kr.or.ddit.vo.MemberVO;

public class MemberVOWrapper implements Principal{
	private final MemberVO realUser;
	
	public MemberVOWrapper(MemberVO realUser) {
		super();
		this.realUser = realUser;
	}
	
	@Override
	public String getName() {
		return realUser.getMemId();
	}
	
	public MemberVO getRealUser() {
		return realUser;
	}
	
}
