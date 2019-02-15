package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.commoncode.domain.model.AbstractCode;

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
@DiscriminatorValue("H0002")
@EntityListeners(AuditingEntityListener.class)
public class DeptType extends AbstractCode implements Serializable {
	
	private static final long serialVersionUID = -7607475813346542493L;		
	
	@Builder
	public DeptType(String code, String codeName, LocalDateTime fromDate, LocalDateTime toDate, Integer sequence) {
		this.code 		= code;
		this.codeName 	= codeName;
		this.fromDate 	= fromDate;
		this.toDate 	= toDate;
		this.sequence 	= sequence;
	}
}
