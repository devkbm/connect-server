package com.like.hrm.employee.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;
import com.like.hrm.employee.domain.entity.enums.DeptType;
import com.like.hrm.employee.domain.entity.enums.JobType;

@Entity
@Table(name = "HRMEMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -3164713918774455925L;

	@Id
	@Column(name="EMP_ID")
	private String id;
	
	@Column(name="EMP_NAME")
	private String name;
		
	@Column(name="RREGNO")
	private String residentRegistrationNumber;
		
	@OneToMany(mappedBy = "employee")
	private List<DeptChangeHistory> deptHistory = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee")
	private List<JobChangeHistory> jobHistory = new ArrayList<>();
	
	public Employee(String id, String name, String residentRegistrationNumber) {
		this.id = id;
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
	}
	
	public void addDeptChange(DeptChangeHistory deptChangeHistory) {
		deptHistory.add(deptChangeHistory);
	}
	
	public void addJobChange(JobChangeHistory jobChangeHistory) {
		jobHistory.add(jobChangeHistory);
	}
	
	public void terminateDept(DeptType deptType, LocalDate terminateDate) {
		
		for (DeptChangeHistory deptHistory: this.deptHistory) {
			if (deptHistory.equalDeptType(deptType) && deptHistory.isEnabled(terminateDate) )
				deptHistory.terminateHistory(terminateDate);				
		}									
		
	}
	
	public void terminateJob(JobType jobType, LocalDate terminateDate) {
		
		for (JobChangeHistory jobHistory: this.jobHistory) {
			if ( jobHistory.isEnabled(terminateDate) )
				jobHistory.terminateHistory(terminateDate);				
		}
	}
	
	
}
