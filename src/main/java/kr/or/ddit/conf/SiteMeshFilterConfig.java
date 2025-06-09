package kr.or.ddit.conf;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import kr.or.ddit.filter.DummyFilter;

@Configuration
public class SiteMeshFilterConfig {
	
	@Bean
	FilterRegistrationBean<DummyFilter> dummyFilter() {
		FilterRegistrationBean<DummyFilter> filter = new FilterRegistrationBean<DummyFilter>();
		filter.setFilter(new DummyFilter());
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE+50);
		filter.addUrlPatterns("/*");
		return filter;
	}
	
//	  <mapping path="/*" decorator="mantisDecorator.jsp"/>
//	   	<!-- 비동기 요청 배제 -->  
//	  <mapping path="/ajax/**" exclude="true" />
//	  <!-- 정적 자원 배제 -->  
//	  <mapping path="/resources/**" exclude="true"/>
	
	@Bean
	FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter() {
		FilterRegistrationBean<ConfigurableSiteMeshFilter> filter = new FilterRegistrationBean<ConfigurableSiteMeshFilter>();
		filter.setFilter(
				ConfigurableSiteMeshFilter.create(builder -> 
					builder.setDecoratorPrefix("/WEB-INF/decorators/")
						.addExcludedPath("/ajax/**")
						.addExcludedPath("/rest/**")
						.addExcludedPath("**/*.html")
						.addDecoratorPath("/*", "mantisDecorator.jsp")
				)
		);
		filter.setOrder(Ordered.LOWEST_PRECEDENCE-100);
		filter.addUrlPatterns("/*");
		return filter;
	}
}
