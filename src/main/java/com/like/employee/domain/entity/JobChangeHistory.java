package com.like.employee.domain.entity;

import java.time.LocalDate;

import com.like.employee.domain.entity.enums.JobType;

public class JobChangeHistory {

	private Long id;
	
	private JobType jobType;
	
	private String jobCode;
	
	private LocalDate fromDate;
	
	private LocalDate toDate;
	
	
}
