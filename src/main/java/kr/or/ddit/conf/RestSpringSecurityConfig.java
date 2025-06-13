package kr.or.ddit.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cors")
@Configuration
public class RestSpringSecurityConfig {
	
	private List<String> allowedOrigins;
	private List<String> allowedMethods;
	private List<String> allowedHeaders;
	private boolean allowCredentials;
	
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(allowedOrigins);
		corsConfig.setAllowedMethods(allowedMethods);
		corsConfig.setAllowedHeaders(allowedHeaders);
		corsConfig.setAllowCredentials(allowCredentials);
		
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
			.csrf(csrf -> 
				csrf.disable()
			)
			.authorizeHttpRequests(authorize ->
				authorize
					.requestMatchers("/rest/auth").permitAll()
					.anyRequest().authenticated()
			)
			.sessionManagement(session -> 
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	
}
