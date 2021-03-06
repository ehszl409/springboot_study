package com.park.myjpa.handler;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.park.myjpa.web.dto.CommonRespDto;

@RestController // 데이터 리턴 
@ControllerAdvice // 모든 Exception을 낚아챔
public class GlobalExceptionHandler {
	

	
	// 그 중 IllegalArgumentException 이 발생하면 해당 함수 실행됨.
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public CommonRespDto<?> dataIntegrityViolation(Exception e){
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgument(Exception e){
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value=EmptyResultDataAccessException.class)
	public CommonRespDto<?> emptyResultDataAccessException(Exception e){
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
}
