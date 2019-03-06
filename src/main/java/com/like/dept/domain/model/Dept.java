package com.like.dept.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 동은정보기술
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)
@Builder
@Entity
@Table(name = "comdept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -969524977226888898L;

	@Id
	@Column(name = "dept_cd")
	String deptCode;

	@Column(name = "dept_nm")
	String deptName;

	@Column(name = "from_dt")
	LocalDate fromDate;

	@Column(name = "to_dt")
	LocalDate toDate;
	
	@Builder.Default
	@Column(name="prt_seq")
	int seq = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_dept_cd")
	Dept parentDept;

	public void changeDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void closeDept(LocalDate toDate) {
		this.toDate = toDate;
	}

}
