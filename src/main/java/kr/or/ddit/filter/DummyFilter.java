package kr.or.ddit.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청 필터링
		log.info("요청 필터링");
		chain.doFilter(request, response);
		// 응답 필터링
		log.info("응답 필터링");
	}
}
