package com.parks.jpaprectice.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.parks.jpaprectice.domain.user.User;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // 컨트롤러의 설정 파일

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			
			// 컨트롤러 실행 직전에 동작
			// 반환값이 true 일 경우 정상적 진행
			// 반환값이 false면 컨트롤러 진입 안함.
			
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				HttpSession session = request.getSession();
				User principal = (User) session.getAttribute("principal");
				if(principal == null) {
					return false;
				} else {					
					return true;
				}

			}
		}).addPathPatterns("/user").addPathPatterns("/post");
	}
}
