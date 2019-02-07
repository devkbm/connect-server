package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;

import com.like.hrm.appointment.domain.model.enums.ChangeType;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
public class CodeDetails {
	
	private Long pkCodeDetails;	
	
	private ChangeType changeType;
	
	private String typeCode;
	
	private String typeName;		
	
	// private Code code;	
	
	
}
