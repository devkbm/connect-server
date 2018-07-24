package com.like.employee.domain.entity;

import java.time.LocalDate;

import com.like.employee.domain.entity.enums.DeptType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeptChangeHistory {

	private String id;
	
	private DeptType deptType;
	
	private String deptCode;
	
	private LocalDate fromDate;
	
	private LocalDate toDate;
			
}
