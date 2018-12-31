package com.like.hrm.appointment.domain.entity;

import com.like.hrm.appointment.domain.entity.enums.ChangeType;
import com.like.hrm.employee.domain.entity.enums.DeptType;
import com.like.hrm.employee.domain.entity.enums.JobType;

public class ChangeInfo {

	private String id;
		
	private String empId;
	
	private ChangeType changeType;
		
	private JobType jobType;
	
	private DeptType deptType;
	
	private String beforeCode;
	
	private String afterCode;
		
}
