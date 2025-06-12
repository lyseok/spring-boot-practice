package kr.or.ddit.conf;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.security.auth.CustomUserDetailService;
import kr.or.ddit.security.oauth2.CustomOidcUserService;
import kr.or.ddit.security.oauth2.OAuth2AuthenticationFailureHandler;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SpringSecurityConfig {
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * 일반 form 로그인 사용자의 정보 조회 
	 * @return
	 */
	@Bean
	public CustomUserDetailService userDetailService() {
		return new CustomUserDetailService(mapper);
	}

	/**
	 * 소셜 로그인 사용자의 정보 조회
	 * @return
	 */
	@Bean
	public CustomOidcUserService customOidcUserService() {
		return new CustomOidcUserService(mapper);
	}
	
	/**
	 * 소셜 로그인 실패시,
	 * 미가입자로 인해 실패라면, 가입 페이지로 리다이렉트 
	 * 그냥 인증 실패라면, 로그인 페이지로 리다이렉
	 * @return
	 */
	@Bean
	public OAuth2AuthenticationFailureHandler failureHandler() {
		return new OAuth2AuthenticationFailureHandler("/member/memberInsert.do");
	}
	
	@Autowired
	private DataSource dataSource;

	// spring boot 로 자동 등록되어있는 객체 주입.
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;


	/**
	 * access token 과 refresh token 을 발급받은 OAuth2AuthorizedClient 객체를 관리하는 객체
	 * memory 나 database 기반으로 토큰 정보를 관리할 수 있음.
	 * spring boot starter 를 사용하는 경우 memory 기반의 관리 객체가 자동 등록됨.
	 * DB 기반으로 관리할 경우, 스키마 필요( classpath:org/springframework/security/oauth2/client/oauth2-client-schema.sql )
	 * @param registrationRepository
	 * @return
	 */
	@Bean
	public OAuth2AuthorizedClientService authorizedClientService() {
	   // return new InMemoryOAuth2AuthorizedClientService(registrationRepository);
	   return new JdbcOAuth2AuthorizedClientService(new JdbcTemplate(dataSource), clientRegistrationRepository);
	}
	
	@Bean // 이미 기본으로 등록되어 있어서 생략해도 무방
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	private final String[] WHITE_LIST = new String[] {
			"/"
			, "/login"
			, "/js/**"
			, "/html/**"
			, "/dist/**"
			, "/error/**"
			, "/swagger-ui/**"
			, "/swagger-ui.html"
			, "/v3/api-docs/**"
			, "/v3/api-docs.yaml"
			, "/oauth2/**"
			, "/member/memberInsert.do"	
	};

	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(List.of("http://localhost:6060", "https://www.naver.com"));
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD"));
		corsConfig.setAllowedHeaders(List.of("*"));
		corsConfig.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource corsConfigurationSource =
				new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/rest/**", corsConfig);
		return corsConfigurationSource;
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/rest/**")
			.cors(cors -> 
				cors.configurationSource(corsConfiguration())
			)
			.authorizeHttpRequests(authorize ->
				authorize.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/**")
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authroize -> 
				authroize
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers(WHITE_LIST).permitAll()
					.requestMatchers(new AntPathRequestMatcher("/mypage")).authenticated()
					.requestMatchers(new AntPathRequestMatcher("/prod/*Insert*")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/prod/*Update*")).hasRole("ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/buyer/**")).hasRole("ADMIN")
					.anyRequest().authenticated()
//					.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
			)
			.formLogin(login -> 
				login
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/", false)
			)
			.oauth2Login(oauth2 -> 
	       		oauth2
	       			.loginPage("/login")
	       			.failureHandler(failureHandler())
			)
			.logout(logout->
				logout
					.logoutUrl("/logout")

			);
		return http.build();
	}
}
