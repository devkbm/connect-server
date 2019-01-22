package com.like.hrm.employee.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum JobType {
	
	/**
	 * 직위
	 */
	POSITION("POSITION", "직위"),
	
	/**
	 * 직급
	 */
	RANK("RANK", "직급"),
	
	/**
	 * 직책
	 */
	DUTY("DUTY", "직책"),
	
	/**
	 * 직무
	 */
	JOB("JOB", "직무");
	
	private String code;
	private String name;
	
	private JobType(final String code, final String name) {
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
