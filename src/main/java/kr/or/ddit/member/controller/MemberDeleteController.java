package kr.or.ddit.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberDeleteController {
	private final MemberService service;
	private final OAuth2AuthorizedClientService authorizedClientService;
	
	@PostMapping("/member/memberDelete.do")
	public String deleteMember(
		@RequestParam("password") String password
		, Authentication authentication
		, HttpSession session
		, RedirectAttributes redirectAttributes
	) {
		log.info("password : {}", password);
//		log.info("id : {}", realUser.getMemId());
		String username = authentication.getName();
		try {
			service.removeMember(username, password);
			
			if(authentication instanceof OAuth2AuthenticationToken) {
				OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
				String clientRegistration = authToken.getAuthorizedClientRegistrationId();
				authorizedClientService.removeAuthorizedClient(clientRegistration, username);
			}
			
			SecurityContextHolder.clearContext();
			session.invalidate();
			return "redirect:/";
		} catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
			return "redirect:/mypage";
		}
	}
}
