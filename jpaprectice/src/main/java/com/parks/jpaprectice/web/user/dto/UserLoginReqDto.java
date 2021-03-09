package com.parks.jpaprectice.web.user.dto;

import lombok.Data;

@Data
public class UserLoginReqDto {
	private String username;
	private String password;

}
