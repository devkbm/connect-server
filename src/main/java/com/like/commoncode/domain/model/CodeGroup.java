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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(callSuper=true, includeFieldNames=true) 
@Entity
@Table(name = "comcodegroup")
@EntityListeners(AuditingEntityListener.class)
public class CodeGroup extends AuditEntity implements Serializable {
		
	private static final long serialVersionUID = -5418758137151108128L;

	@Id		
	@Column(name="code_group")
	String codeGroup;
	
	@NotEmpty(message="코드그룹명은 필수 입력 사항입니다.")
	@Column(name="code_group_name")
	String codeGroupName;
	
	@Column(name="fixed_length_yn")
	boolean fixedLengthYn;
	
	@Column(name="code_length")
	int codeLength;
	
	@Column(name="cmt")
	String cmt;				
	
	public String getId() {
		return this.codeGroup;
	}
}
