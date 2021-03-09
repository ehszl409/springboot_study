package com.parks.jpaprectice.config;

import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.parks.jpaprectice.filter.MyAuthenticationFilter;



// @Component도 상관 없긴 하다. 
// 하지만 환경 설정에 관한건 @Configuration 적어줘서 IoC에 띄워주는 것이 적합하다.

// @Configuration을 붙이면 web.xml파일에 추가 시켜주는 로직이 포함되어 있다.
@Configuration
public class FilterConfig {

	
	// 이 함수의 목적은 필터를 가진 Bean을 IoC에 등록시켜 주기위한 목적이다.
	@Bean
	public FilterRegistrationBean authenticationFilterRegister() {
		
		// 방법
		// 1. 필터 객체 생성
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(new MyAuthenticationFilter());
		
		// 필터가 실행되는 조건
		bean.addUrlPatterns("/test");
		
		// 필터의 순서를 숫자로 설정할 수 있다.
		// 낮은 순서로 먼저 실행된다.
		bean.setOrder(0);
		
		// 2. retuen 
		// FilterRegistrationBean이 MyAuthenticationFilter을
		// 객체로 만들어 주고 레퍼런스를 리턴 해줌으로 
		// IoC에 MyAuthenticationFilter가 등록된다.
		return bean;
	}
	
}
