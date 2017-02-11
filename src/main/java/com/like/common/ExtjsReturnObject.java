package com.like.common;

import java.util.List;

import lombok.Data;

@Data
public class ExtjsReturnObject {

	List<?> recv;
	
	int total;
	
	boolean success;
	
	String message;
	
	public ExtjsReturnObject(List<?> recv,int total,boolean success,String message) {
		this.recv = recv;
		this.total = total;
		this.success = success;
		this.message = message;
	}
}
