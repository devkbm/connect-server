package com.like.hrm.employee.domain.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeptType {
	REGULAR("REGULAR","REGULAR"), 
	
	TEMP("TEMP","TEMP");
	
	private String code;
	private String name;
	
	private DeptType(final String code, final String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	
	
}
