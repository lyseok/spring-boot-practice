package kr.or.ddit.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login/logout";
	}
}
