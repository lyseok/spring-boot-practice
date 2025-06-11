package kr.or.ddit.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.vo.MemberVO;

public class MemberVOWrapper implements UserDetails, RealUserWrapper<MemberVO>{

	private final MemberVO realUser;
	
	public MemberVOWrapper(MemberVO realUser) {
		super();
		this.realUser = realUser;
	}

	@Override
	public MemberVO getRealUser() {
		return realUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(realUser.getMemRole());
	}

	@Override
	public String getPassword() {
		return realUser.getMemPassword();
	}

	@Override
	public String getUsername() {
		return realUser.getMemId();
	}

}
