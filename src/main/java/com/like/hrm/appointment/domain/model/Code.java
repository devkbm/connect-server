package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@Table(name = "HRMAPPCODE")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AuditEntity implements Serializable {

	private String code;
	
	private String name;
	
	private List<CodeDetails> codedetails = new ArrayList<>();		
	
}
