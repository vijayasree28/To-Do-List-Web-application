package com.task.model;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	
	@Transient
	private String ErrorCode;

	@Transient
	private String ErrorMsge;

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMsge() {
		return ErrorMsge;
	}

	public void setErrorMsge(String errorMsge) {
		ErrorMsge = errorMsge;
	}
	
	

}
