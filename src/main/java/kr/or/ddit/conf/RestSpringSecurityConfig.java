package kr.or.ddit.conf;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.JWSAlgorithm;

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
	
	@Value("${jwt.secrete-key}")
	private byte[] secreteKey;
	
	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKey key = new SecretKeySpec(secreteKey, JWSAlgorithm.HS256.getName());
		NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(key)
						.macAlgorithm(MacAlgorithm.HS256)
						.build();
		return decoder;
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
//					.requestMatchers("/rest/buyer/**").hasAnyAuthority("SCOPE_BUYER")
//					.requestMatchers(HttpMethod.GET, "/rest/lprod/**").hasAuthority("SCOPE_LPROD/READ")
//					.requestMatchers(HttpMethod.DELETE, "/rest/lprod/**").hasAuthority("SCOPE_LPROD/DELETE")
//					.requestMatchers("/rest/lprod").hasAuthority("SCOPE_LPROD/WRITE")
					.requestMatchers("/rest/lprod/**").hasRole("ADMIN")
					.anyRequest().authenticated()
			)
			.sessionManagement(session -> 
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.oauth2ResourceServer(oauth2RS ->
				oauth2RS.jwt(jwt -> jwt.decoder(jwtDecoder()))
			)
			;
		
		return http.build();
	}
	
	
}
