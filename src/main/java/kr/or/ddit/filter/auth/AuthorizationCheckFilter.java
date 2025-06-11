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
public class AuthorizationCheckFilter extends HttpFilter{
	private final Map<String, List<String>> securedResources; 
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 보호자원에 대한 요청인지 판단
		// 보호자원 요청
		
		AntPathMatcher matcher = new AntPathMatcher();
		
		String uri = request.getRequestURI();
		boolean secured = false;
		boolean pass = true;
		
		for(String key : securedResources.keySet()) {
			secured = matcher.match(key, uri);
			if(secured) {
				MemberVOWrapper principal = (MemberVOWrapper) request.getUserPrincipal();
				String memRole = principal.getRealUser().getMemRole();
				pass = securedResources.get(key).contains(memRole);
				break;
			}
		}

		if(pass) {
			chain.doFilter(request, response);
		} else {
			response.sendError(403, "권한이 없는 사용");
		}
		
//		String uri = request.getRequestURI();
//		AntPathMatcher matcher = new AntPathMatcher();
//		
//		boolean secured = false;
//		List<String>  allowedRoles = null;
//		for(String key : securedResources.keySet()) {
//			secured = matcher.match(key, uri);
//			if(secured) {
//				allowedRoles = securedResources.get(key);
//				break;
//			}
//		}
//		boolean pass = true;
//		
//		if(secured) {
//			// 권한 획득 여부 판단 
//			// 권한 소유 여부 : 자원에 설정된 접근 권한과 사용자가 부여받은 역할의 일치 여부
//			MemberVOWrapper principal = (MemberVOWrapper) request.getUserPrincipal();
//			String userRole = principal.getRealUser().getMemRole();
//			pass = allowedRoles.contains(userRole);		
//		} else {
//			// 비보호자원 요청, 통과
//			pass = true;
//		}
		
//		if(pass) {
//			// 인가된 사용자(권한있음), 통과
//			chain.doFilter(request, response);
//		} else {
//			// 비인가 사용자(권한 없음), 403에러발생 
//			response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한 없음");
//		}
		
	}
}
