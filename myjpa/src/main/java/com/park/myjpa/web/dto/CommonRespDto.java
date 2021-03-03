package com.park.myjpa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonRespDto<T> {
	private int statusConde; //1 정상 -1 실채
	private String msg; // 오류 내용
	private T data;
}
