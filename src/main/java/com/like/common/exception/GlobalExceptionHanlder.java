package com.like.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHanlder {
	
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason ="오류")
	@ExceptionHandler(value = ControllerException.class)  
    public ResponseEntity<String> handleException(Exception e){		
					   
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);	   
	}
	
}
