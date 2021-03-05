package com.park.myjpa.web.user.dto;

import com.park.myjpa.domain.post.Post;
import com.park.myjpa.domain.user.User;

import lombok.Data;

@Data
public class UserJoinReqDto {
	private String username;
	private String password;
	private String email;
	
	// 본 오브젝트로 바꿔주는 함수를 만들어 준다.
		public User toEntity() {
			return User.builder()
					.username(username)
					.password(password)
					.email(email)
					.build();
		}
}
