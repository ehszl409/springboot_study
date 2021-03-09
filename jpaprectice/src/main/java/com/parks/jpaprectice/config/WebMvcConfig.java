package com.parks.jpaprectice.config;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.parks.jpaprectice.domain.user.User;

// WebMvcConfigurer은 컨트롤러의 설정파일 이다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // view컨트롤, Model컨트롤

	// 인터셉터 만들기
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 1. 인터셉터 등록하기
		registry.addInterceptor(new HandlerInterceptor() {
			
			// 2. 인터셉터 동작 순간 정하기
			// 컨트롤러 실행 직전에 동작
			// 반환값이 true 일 경우 정상적 진행
			// 반환값이 false면 컨트롤러 진입 안함.
			@Override
			public boolean preHandle(HttpServletRequest request, 
					HttpServletResponse response, Object handler)
					throws Exception {
				// 3. 내용 구현하기 
				HttpSession session = request.getSession();
				User principal = (User) session.getAttribute("principal");
				if(principal == null) {
					throw new AuthenticationException("로그인을 해주세요");
					//return false;
				} else {					
					return true;
				}

			} // 4. 인터셉터 동작 조건 설정
		}).addPathPatterns("/user").addPathPatterns("/post"); 
	}
}
