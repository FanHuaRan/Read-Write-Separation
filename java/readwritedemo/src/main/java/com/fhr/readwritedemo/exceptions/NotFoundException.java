package com.fhr.readwritedemo.exceptions;

import org.springframework.http.HttpStatus;
/**
 * NotFound自定义异常
 * @author fhr
 * @date 2017/07/31
 */
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8070122108089783299L;
	
	private final HttpStatus httpStatus;
	
	public NotFoundException(HttpStatus httpStatus,String message){
		super(message);
		this.httpStatus=httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
