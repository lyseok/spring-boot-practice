package kr.or.ddit.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController {
	private static final String MODEL_NAME = "member";
	private final MemberService service;
	
//	@ModelAttribute(MODEL_NAME)
//	public MemberVO member(
//		Authentication principal
//	) {
//		String username = principal.getName();
//		MemberVO member = service.readMember(username);
//		if (member == null) {
//			return new MemberVO();			
//		} else {
//			return member;
//		}
//	}
	
	@GetMapping
	public String updateForm(Authentication principal, Model model) {
		String username = principal.getName();
		if(!model.containsAttribute(MODEL_NAME)) {
			MemberVO member = service.readMember(username);
			model.addAttribute(MODEL_NAME, member);
		}
		return "member/memberEdit";
	}
	
	@PostMapping
	public String updateProcess(
		@Valid @ModelAttribute(MODEL_NAME) MemberVO member, BindingResult errors
		, RedirectAttributes redirectAttributes		
	) {
		String lvn;
		if(!errors.hasErrors()) {
			// 수정 성공 mypage로 이동
			try {
				service.modifyMember(member);
				lvn = "redirect:/mypage";				
			} catch (AuthenticationException e) {
				// 인증 실패 수정 양식으로 redirect, 오류 메시지, 입력 정
				redirectAttributes.addFlashAttribute(MODEL_NAME, member);
				redirectAttributes.addFlashAttribute("message", "패스워드가 일치하지 않음");
				
				lvn = "redirect:/member/memberUpdate.do";
			}
		} else {
			// 검증 실패? 수정양식으로 redirect, 기존 입력 데이터, 검증 에러 메시지
			redirectAttributes.addFlashAttribute(MODEL_NAME, member);
			
			String errorName = BindingResult.MODEL_KEY_PREFIX + MODEL_NAME;
			redirectAttributes.addFlashAttribute(errorName, errors);
			lvn = "redirect:/member/memberUpdate.do";	
		}
		
		return lvn;
	}
	
}
