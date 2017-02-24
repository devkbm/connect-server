package com.like.common.web.response;

import java.util.List;

import lombok.Data;

@Data
public class ExtjsReturnObject {

	List<?> data;
	
	int total;
	
	boolean success;
	
	String message;
	
	public ExtjsReturnObject(List<?> data,int total,boolean success,String message) {
		this.data = data;
		this.total = total;
		this.success = success;
		this.message = message;
	}
}
