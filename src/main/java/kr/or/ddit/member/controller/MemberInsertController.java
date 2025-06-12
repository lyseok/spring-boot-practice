package kr.or.ddit.member.controller;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.common.exception.PKDuplicatedException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.security.oauth2.UserNotRegisteredException;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	private final MemberService service;
	private final String MODEL_NAME = "member";
	
	@ModelAttribute(MODEL_NAME)
	public MemberVO member(
		@SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
	) {
		MemberVO member = new MemberVO();
		if(lastException != null) {			
			ClientRegistration unRegisteredUser = lastException.getClientRegistration();
			member.setMemMail(lastException.getUnRegisteredUser().getEmail());
		}
		return member;
	}
	
	@GetMapping
	public String signForm() {
		return "member/memberForm";
	}
	
	@PostMapping
	public String singupProcess(
		@Valid @ModelAttribute(MODEL_NAME) MemberVO member, BindingResult errors
		, RedirectAttributes redirectAttributes
//		, @SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) UserNotRegisteredException lastException
	) {
		String lvn;
		if(!errors.hasErrors()) {
			try {
				service.createMember(member);
				lvn = "redirect:/";
//				if(lastException != null) {
//					String registrationId = lastException.getClientRegistration().getRegistrationId();
//					lvn = "redirect:" + OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
//							+ "/" + registrationId;
//				}
			} catch (PKDuplicatedException e) {
				// 아이디중복
				redirectAttributes.addFlashAttribute(MODEL_NAME, member);
				redirectAttributes.addFlashAttribute("message", "아이디 중복");
				
				lvn = "redirect:/member/memberInsert.do";				
			}
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, member);
			String errorsName = BindingResult.MODEL_KEY_PREFIX + MODEL_NAME;
			redirectAttributes.addFlashAttribute(errorsName, errors);
			lvn = "redirect:/member/memberInsert.do";
		}
		return lvn;
	}
}
