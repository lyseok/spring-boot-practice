package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.util.AntPathMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutheticationCheckFilter extends HttpFilter {
	private final Map<String, List<String>> securedResources; 
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 현재 요청이 보호자원에 대한 요청인지 판단
		String uri = request.getRequestURI();
		AntPathMatcher matcher = new AntPathMatcher();
		
		boolean secured = false;
		for(String key : securedResources.keySet()) {
			secured = matcher.match(key, uri);
			if(secured) break;
		}
		boolean pass = true;
		
		if(secured) {
		// 보호자원 요청 
			// 인증 상태 여부 판단
			if(request.getUserPrincipal() != null) {
				// 인증상태, 통과 
				pass = true;
			} else {
				// 미인증상태, 로그인폼으로 이동
				pass = false;
			}
			
		} else {
		// 비보호자원 요청, 통과
			pass = true;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
		
	}
}
