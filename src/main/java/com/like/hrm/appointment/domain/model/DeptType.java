package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.like.common.domain.AuditEntity;
import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>부서 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@Table(name = "cmcode")
@EntityListeners(AuditingEntityListener.class)
public class DeptType extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -7607475813346542493L;
	
	@JsonUnwrapped
	@EmbeddedId		
	private String id;
		
	@Column(name="code_name")
	private String codeName;
	
	@Column(name="from_dt")
	private LocalDateTime fromDate;
	
	@Column(name="to_dt")
	private LocalDateTime toDate;
	
	@Column(name="seq")
	private Integer sequence;
	
	@Builder
	public DeptType(String code, String codeName, LocalDateTime fromDate, LocalDateTime toDate, Integer sequence) {
		this.id 		= code;		
		this.codeName 	= codeName;
		this.fromDate 	= fromDate;
		this.toDate 	= toDate;
		this.sequence 	= sequence;
	}
}
