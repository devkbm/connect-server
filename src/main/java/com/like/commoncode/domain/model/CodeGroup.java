package com.like.commoncode.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ToString(callSuper=true, includeFieldNames=true) 
@Entity
@Table(name = "cmcodegroup")
@EntityListeners(AuditingEntityListener.class)
public class CodeGroup extends AuditEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5418758137151108128L;

	@Id		
	@Column(name="code_group")
	private String codeGroup;
	
	@NotEmpty(message="코드그룹명은 필수 입력 사항입니다.")
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
		
	
	protected CodeGroup() {}
	
	public CodeGroup(String codeGroup, String codeGroupName) {
		this.codeGroup = codeGroup;
		this.codeGroupName = codeGroupName;
		
		this.sysUser = "test";
    	this.updUser = "test";    	    	
	}
}
