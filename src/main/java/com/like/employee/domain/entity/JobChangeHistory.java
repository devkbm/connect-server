package com.like.employee.domain.entity;

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
import com.like.employee.domain.entity.enums.JobType;


@Entity
@Table(name = "HRMEMPDEPTHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class JobChangeHistory extends AuditEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
		
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="DEPT_TYPE")
	private JobType jobType;
	
	@Column(name="DEPT_CODE")
	private String jobCode;
	
	@Column(name="FROM_DT")
	private LocalDate fromDate;
	
	@Column(name="TO_DT")
	private LocalDate toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;

	public JobChangeHistory(JobType jobType, String jobCode, LocalDate fromDate, LocalDate toDate) {		
		this.jobType = jobType;
		this.jobCode = jobCode;
		this.fromDate = fromDate;
		this.toDate = toDate;		
	}	
	
}
