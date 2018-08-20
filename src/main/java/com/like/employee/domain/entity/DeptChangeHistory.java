package com.like.employee.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.common.domain.AuditEntity;
import com.like.employee.domain.entity.enums.DeptType;

public class DeptChangeHistory extends AuditEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="DEPT_TYPE")
	private DeptType deptType;
	
	@Column(name="DEPT_CODE")
	private String deptCode;
	
	@Column(name="FROM_DT")
	private LocalDate fromDate;
	
	@Column(name="TO_DT")
	private LocalDate toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public DeptChangeHistory(DeptType deptType, String deptCode, LocalDate fromDate, LocalDate toDate) {
		this.deptType = deptType;
		this.deptCode = deptCode;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
			
}
