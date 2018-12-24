package com.like.hrm.appointment.domain.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ChangeType {

	// 부서정보	
	REGULAR("REGULAR","REGULAR"), 
	
	TEMP("TEMP","TEMP"),
	
	// 인사정보	
	POSITION("POSITION", "직위"),
		
	RANK("RANK", "직급"),
		
	DUTY("DUTY", "직책"),
	
	JOB("JOB", "직무");
	
	private String code;
	private String name;
	
	private ChangeType(final String code, final String name) {
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
