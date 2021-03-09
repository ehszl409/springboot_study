package com.parks.jpaprectice.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parks.jpaprectice.domain.user.User;
import com.parks.jpaprectice.service.UserService;
import com.parks.jpaprectice.web.dto.CommonRespDto;
import com.parks.jpaprectice.web.user.dto.UserJoinReqDto;
import com.parks.jpaprectice.web.user.dto.UserLoginReqDto;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserContoller {
	
	private final HttpSession session;
	private final UserService userService;
	
	// 인증도 필요 없음 = 그래서 주소를 /join으로 만듬
	@PostMapping("/join")
	public CommonRespDto<?> join(@RequestBody UserJoinReqDto userJoinReqDto){
		return new CommonRespDto<>(1, "성공",userService.회원가입(userJoinReqDto) );
	}
	
	// 인증도 필요 없음 = 그래서 주소를 /login으로 만듬
	@PostMapping("/login")
	public CommonRespDto<?> login(@RequestBody UserLoginReqDto userLoginReqDto){
		
		User userEntity = userService.로그인(userLoginReqDto);
		
		if(userEntity == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {			
			session.setAttribute("principal", userEntity);
			return new CommonRespDto<>(1, "성공",userEntity);
		}
	}
	
	// 인증만 필요
	@GetMapping("/user")
	public CommonRespDto<?> findAll(){
		return new CommonRespDto<>(1, "성공", userService.전체찾기());
	}
	
	// 인증만 필요
	@GetMapping("/user/{id}")
	public CommonRespDto<?> findById(@PathVariable long id){
		return new CommonRespDto<>(1, "성공", userService.한건찾기(id));
	}
}
