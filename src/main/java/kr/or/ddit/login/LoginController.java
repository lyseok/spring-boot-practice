package kr.or.ddit.login;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//@RequestMapping("")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm(
		@SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception lastException 
		, HttpSession session
		, Model model
	) {
		if(lastException != null) {
			model.addAttribute("message", lastException.getMessage());
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		return "login/loginForm";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login/logout";
	}
}
