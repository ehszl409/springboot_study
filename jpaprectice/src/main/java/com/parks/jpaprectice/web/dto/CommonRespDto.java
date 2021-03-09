package com.parks.jpaprectice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRespDto<T>{
	private int statusCode;
	private String result;
	private T data;
}
