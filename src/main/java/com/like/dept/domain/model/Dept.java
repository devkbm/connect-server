package com.like.dept.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "dept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -969524977226888898L;

	@Id	
	@Column(name="dept_cd")
	private String deptCode;
	
	@Column(name="dept_nm")
	private String deptName;
		
	@Column(name="use_yn")
	private Boolean isUse;
	
	@Column(name="from_dt")
	private LocalDate fromDate;
	
	@Column(name="to_dt")
	private LocalDate toDate;
	
}
