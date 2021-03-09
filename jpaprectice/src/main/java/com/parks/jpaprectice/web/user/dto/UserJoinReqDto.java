package com.parks.jpaprectice.web.user.dto;

import com.parks.jpaprectice.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinReqDto {
	private String username;
	private String password;
	private String email;
	
	public User toEntity() {
		return new User().builder()
				.username(this.username)
				.password(this.password)
				.email(this.email)
				.build();
	}
}
