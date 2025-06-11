package kr.or.ddit.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.security.auth.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SpringSecurityConfig {
	@Autowired
	private MemberMapper mapper;
	
	@Bean
	public CustomUserDetailService userDetailService() {
		return new CustomUserDetailService(mapper);
	}
	
	@Bean // 이미 기본으로 등록되어 있어서 생략해도 무방
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authroize -> 
				authroize
					.requestMatchers(new AntPathRequestMatcher("/mypage")).authenticated()
					.requestMatchers(new AntPathRequestMatcher("/prod/*Insert*")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/prod/*Update*")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/buyer/**")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
			)
			.formLogin(login -> 
				login
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/", false)
			)
			.logout(logout->
				logout
					.logoutUrl("/logout")

			);
		return http.build();
	}
}
