package kr.or.ddit.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.security.auth.MemberVOWrapper;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyPageController {
	private final MemberService service;
	
	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal MemberVOWrapper principal, Model model) {
		String username = principal.getUsername();
		MemberVO member = service.readMember(username);
		model.addAttribute("member", member);
		return "member/mypage";
	}
}
