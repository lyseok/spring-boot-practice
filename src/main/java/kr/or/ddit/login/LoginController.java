package kr.or.ddit.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
//@RequestMapping("")
@RequiredArgsConstructor
public class LoginController {
	private final AuthenticateService service;
	
	@GetMapping("/login")
	public String loginForm() {
		return "login/loginForm";
	}
	@PostMapping("/login")
	public String loginCheck(
		HttpSession session
		, @RequestParam String username
		, @RequestParam String password	
		, RedirectAttributes redirectAttributes
	) {
		
		String dest = null;
		String message = null;
//		3. 파라미터 검증
		
		// 4. 검증 통과
		MemberVO inputData = new MemberVO();
		inputData.setMemId(username);
		inputData.setMemPassword(password);
		// 4-1. 인증 여부 판단
		if(service.authenticate(inputData)) {
			// 4-2. 인증 성공: 웰컴페이지 이동
			// 1) Principal 구현 객체 생성
			// 2) request.getUserPrincipal에서 반환될 수 있도록 세팅
			// --> Web Filter의 활용
			
			dest = "redirect:/";
			session.setAttribute("authUser", inputData);
		} else {
			// 4-3. 인증 실패 : 로그인 페이지 이동
			dest = "redirect:/login";
			message = "아이디나 비밀번호가 서로 다른 경우, 로그인 실패";
		} // if(authenticate(username, password)) end
			
		if(StringUtils.isNotBlank(message)) {
			redirectAttributes.addFlashAttribute("message", message);
		}
		
		return dest;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
