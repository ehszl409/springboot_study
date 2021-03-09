package com.parks.jpaprectice.handler;

import javax.security.sasl.AuthenticationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.parks.jpaprectice.web.dto.CommonRespDto;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	// DataIntegrityViolationException
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CommonRespDto<?> dataIntegrityViolationException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public CommonRespDto<?> httpMessageNotReadableException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public CommonRespDto<?> httpRequestMethodNotSupportedException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}

	// IllegalArgumentException
	@ExceptionHandler(value = IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgumentException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public CommonRespDto<?> authenticationException(Exception e) {
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}

}
