package com.park.myjpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.park.myjpa.domain.user.User;
import com.park.myjpa.domain.user.UserRepository;
import com.park.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public List<UserRespDto> 전체찾기() {
		List<User> usersEntity = userRepository.findAll();

		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : usersEntity) {
			userRespDtos.add(new UserRespDto(user));
		}
		return userRespDtos;
	}
	
	public void 한건찾기() {
		
	}
	
	public void 프로파일() {
		
	}
	
	public void 로그인() {
		
	}
	
	public void 회원가입() {
		
	}
}
