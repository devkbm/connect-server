package com.like.employee.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Employee {

	private String id;
	
	private String name;
		
	private String residentRegistrationNumber;
	
	private List<DeptChangeHistory> deptHistory;
	
	private List<JobChangeHistory> jobHistory;
	
}
