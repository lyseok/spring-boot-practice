package kr.or.ddit.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService{
	
	@Autowired
	private final MemberMapper mapper;
	
	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		 
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		
		log.info("oidc Provider : {}", clientRegistration);  
		log.info("oidc : {}", oidcUser);
		log.info("oidc user name : {}", oidcUser.getName());
		log.info("oidc other attribute : {}", oidcUser.getAttributes());
		log.info("oidc authorities : {}", oidcUser.getAuthorities());
		
		log.info("oidc full name : {}", oidcUser.getFullName());
		log.info("oidc nickname : {}", oidcUser.getNickName());
		log.info("oidc email : {}", oidcUser.getEmail());
		log.info("oidc email verified : {}", oidcUser.getEmailVerified());
		log.info("oidc picture : {}", oidcUser.getPicture());
		String userNameAttributeName =  clientRegistration.getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();
		
		log.info("oidc user name : {}", oidcUser.getAttributes().get(userNameAttributeName));
		
		String username =  oidcUser.getEmail();
		
 		MemberVO realUser = mapper.selectMemberByMail(username);
 		
		if(realUser==null) {
			throw new UserNotRegisteredException(oidcUser, clientRegistration);
		}else if(realUser.isMemDelete()) {
			throw new OAuth2AuthenticationException(new OAuth2Error("deleted-user"), "이미 탈퇴한 회원입니다.");
		}
		
		 return new Oauth2MemberVOWrapper(oidcUser, realUser, "dummy");
	}

}