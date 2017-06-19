package com.like.code.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "cmcodegroup")
@EntityListeners(AuditingEntityListener.class)
public class CommonCodeGroup extends AuditEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5418758137151108128L;

	@Id		
	@Column(name="code_group")
	private String codeGroup;
	
	@Column(name="code_group_name")
	private String codeGroupName;
	
	@Column(name="code_length")
	private int codeLength;
	
	@Column(name="cmt")
	private String cmt;
	
	@Column(name="enum_yn")
	private boolean enumYn;
	
	@Column(name="enum_package")
	private String enumPackage;
	
	@Column(name="enum_name")
	private String enumName;
		
	
}
