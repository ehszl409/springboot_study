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

// import javax.servlet.Filter; 해당 필터를 implements
// 이 클래스는 IoC에 띄워지지 않는다.
public class MyAuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("나의 인증 필터");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		// 로그인을 했는지 않았는지 필터를 통해 체크를 해준다.
		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			// 로그인이 안되어 있다면 응답없도록 설정.
			// 사용자에게 로그인이 안되어 있다고 응답해주는 것이 올바르기에
			// PrintWrite나 익셉션처리를 해줘야한다.
			// PrintWrite는 굉장히 번거롭거 귀찮은 일이기에
			// 글로벌 익셉션 처리는 해주는 것이 올바르다.
			return;
			//throw new AuthenticationException();
		} else {
			chain.doFilter(request, response);
		}

		// 필터를 거치고 스프링 서버 디스패처로 진입한다.
		// chain.doFilter(request, response);

	}

}
