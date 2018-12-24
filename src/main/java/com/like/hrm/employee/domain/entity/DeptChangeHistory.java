package com.like.hrm.employee.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.like.common.domain.AuditEntity;
import com.like.hrm.employee.domain.entity.enums.DeptType;

@Entity
@Table(name = "HRMEMPDEPTHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class DeptChangeHistory extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1910423657477232102L;
	
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
	
	public boolean isEnabled(LocalDate date) {
		return date.isAfter(fromDate) && date.isBefore(toDate) ? true : false;		
	}
	
	public void terminateHistory(LocalDate date) {
		this.toDate = date;
	}
	
	public boolean equalDeptType(DeptType deptType) {
		return this.deptType.equals(deptType) ? true : false;
	}
			
}
