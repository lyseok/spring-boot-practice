package kr.or.ddit.login;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("")
public class LoginController {
	
	@GetMapping("${myapp.login-url}")
	public String loginForm(
		@SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception lastException 
		, HttpSession session
		, Model model
		, Authentication authentication
		, RedirectAttributes redirectAttributes
	) {
		if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			redirectAttributes.addFlashAttribute("message", "로그인 이미 했음");
			return "redirect:/";
		}
			
		if(lastException != null) {
			model.addAttribute("message", lastException.getMessage());
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		return "login/loginForm";
	}
	
	@GetMapping("${myapp.logout-url}")
	public String logout() {
		return "login/logout";
	}
}
