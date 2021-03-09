package com.parks.jpaprectice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parks.jpaprectice.domain.user.User;
import com.parks.jpaprectice.domain.user.UserRepository;
import com.parks.jpaprectice.web.user.dto.UserJoinReqDto;
import com.parks.jpaprectice.web.user.dto.UserLoginReqDto;
import com.parks.jpaprectice.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepo;

	public User 회원가입(UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepo.save(userJoinReqDto.toEntity());
		return userEntity;
	}

	public User 로그인(UserLoginReqDto userLoginReqDto) {
		// userRepo의 리턴값이 User오브젝트에 username과 password를 바꿔준다.
		User userEntity = userRepo.findByUsernameAndPassword(userLoginReqDto.getUsername(),
				userLoginReqDto.getPassword());
		return userEntity;
	}

	public List<UserRespDto> 전체찾기() {
		List<User> userEntity = userRepo.findAll();

		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : userEntity) {
			userRespDtos.add(new UserRespDto(user));
		}

		return userRespDtos;
	}
	
	public User 한건찾기(long id) {
		User userEntity = userRepo.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		return userEntity;
	}

}
