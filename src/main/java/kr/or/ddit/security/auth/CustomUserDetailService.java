package kr.or.ddit.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
	private final MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO realUser = mapper.selectMember(username);
		if(realUser == null) {
			throw new UsernameNotFoundException(String.format("%s 그런 회원 없음", username));
		}
		return new MemberVOWrapper(realUser);
	}
	

}
