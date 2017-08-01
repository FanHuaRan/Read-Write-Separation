package com.fhr.readwritedemo.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorException extends RuntimeException {
	private static final long serialVersionUID = 807012218089783299L;
	
	private HttpStatus httpStatus;
	
	public ErrorException(HttpStatus httpStatus,String message){
		super(message);
		this.httpStatus=httpStatus;
	}
}
