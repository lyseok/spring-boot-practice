package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.security.Principal;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.vo.MemberVO;

public class GeneratePrincipalFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequestWrapper wrapper = 
				new HttpServletRequestWrapper(req) {
			@Override
			public Principal getUserPrincipal() {
				HttpSession session = getSession(false);
				MemberVO authUser = null;
				if(session != null) {
					authUser = (MemberVO)session.getAttribute("authUser");					
				}
				if(authUser!=null) {
					return new MemberVOWrapper(authUser);
				} else {
					return null;
				}
			}
		};
		chain.doFilter(wrapper, res);
	}
}
