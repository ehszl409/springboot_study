package com.parks.jpaprectice.config;

import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.parks.jpaprectice.filter.MyAuthenticationFilter;



// @Component도 상관 없긴 하다. 환경 설정에 관한건 @Configuration 
@Configuration
public class FilterConfig {

	
	@Bean
	public FilterRegistrationBean authenticationFilterRegister() {
		// 필터 객체 생성
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(new MyAuthenticationFilter());
		
		// 필터가 실행되는 조건
		bean.addUrlPatterns("/test");
		
		// 필터의 순서를 숫자로 설정할 수 있다.
		bean.setOrder(0);
		
		// IOC에 등록 하기위한 리턴
		return bean;
	}
	
}
