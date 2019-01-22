package com.like.hrm.appointment.domain.model;

import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.employee.domain.model.enums.DeptType;
import com.like.hrm.employee.domain.model.enums.JobType;

public class ChangeInfo {

	private String id;
		
	private String empId;
	
	private ChangeType changeType;
		
	private JobType jobType;
	
	private DeptType deptType;
	
	private String beforeCode;
	
	private String afterCode;
		
}
