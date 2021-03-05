package com.park.myjpa.web.user.dto;

import com.park.myjpa.domain.user.User;

import lombok.Data;

@Data
public class UserLoginReqDto {

	private String username;
	private String password;
	
	// 로그인때는 select하기 때문에 필요없다. 
			public User toEntity() {
				return User.builder()
						.username(username)
						.password(password)
						.build();
			}
	
}
