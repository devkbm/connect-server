package com.like.common.web.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHanlder {
	
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason ="오류")
	@ExceptionHandler(value = ControllerException.class)  
    public void handleException(Exception e, HttpServletResponse response) throws IOException{		
		
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());;
		
		//WebControllerUtil.getResponse(null, 0, false, "조회시 오류가 발생하였습니다.", HttpStatus.OK);
	}
	
}
