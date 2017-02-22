package com.like.common.exception;

public class ControllerException extends RuntimeException {
	
	private String message;
	
	public ControllerException(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}

}
