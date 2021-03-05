package com.park.myjpa.web.user.dto;

import java.time.LocalDateTime;

import com.park.myjpa.domain.user.User;

import lombok.Data;

// user만 전달하는 DTO
@Data
public class UserRespDto {

	private long id;
	private String username;
	private String password;
	private String email;
	private LocalDateTime createDate;
	
	public UserRespDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.createDate = user.getCreateDate();
	}
	
}
