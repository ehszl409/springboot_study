package com.parks.jpaprectice.filter;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.parks.jpaprectice.domain.user.User;

public class MyAuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("나의 인증 필터");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		
		  User principal = (User) session.getAttribute("principal");
		  
		  if(principal == null ) { 
			  throw new AuthenticationException();
		} else {
		  chain.doFilter(request, response); }
		 

		chain.doFilter(request, response);

	}

}
