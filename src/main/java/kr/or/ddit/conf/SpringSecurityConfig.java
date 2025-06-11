package kr.or.ddit.conf;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.filter.auth.AutheticationCheckFilter;
import kr.or.ddit.filter.auth.AuthorizationCheckFilter;
import kr.or.ddit.filter.auth.GeneratePrincipalFilter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SpringSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public Map<String, List<String>> securedResources(
			@Value("classpath:kr/or/ddit/SecuredResources.properties") Resource securedResourcesFile
			) throws IOException {
		Map<String, List<String>> securedMap = new HashMap<>();
		
		Properties securedProps = PropertiesLoaderUtils.loadProperties(securedResourcesFile);
		for(String url : securedProps.stringPropertyNames()) {
			String roles = securedProps.getProperty(url);
			
			securedMap.put(url, Arrays.asList(roles.split(",")));
			log.info("보호자원 추가 : {} - {}", url, roles);
		}
		return securedMap;
	}
	
	@Bean
	public FilterRegistrationBean<GeneratePrincipalFilter> principalFilter() {
		FilterRegistrationBean<GeneratePrincipalFilter> filter = new FilterRegistrationBean<GeneratePrincipalFilter>();
		filter.setFilter(new GeneratePrincipalFilter());
		filter.setOrder(100);
		filter.addUrlPatterns("/*");
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean<AutheticationCheckFilter> authenticationFilter(
		Map<String, List<String>> securedResources
	) {
		FilterRegistrationBean<AutheticationCheckFilter> filter = new FilterRegistrationBean<AutheticationCheckFilter>();
		filter.setFilter(new AutheticationCheckFilter(securedResources));
		filter.setOrder(101);
		filter.addUrlPatterns("/*");
		return filter;
	}
	
	@Bean
	public FilterRegistrationBean<AuthorizationCheckFilter> authorizationCheckFilter(
			Map<String, List<String>> securedResources
			) {
		FilterRegistrationBean<AuthorizationCheckFilter> filter = new FilterRegistrationBean<AuthorizationCheckFilter>();
		filter.setFilter(new AuthorizationCheckFilter(securedResources));
		filter.setOrder(102);
		filter.addUrlPatterns("/*");
		return filter;
	}
}
