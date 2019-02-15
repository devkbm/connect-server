package com.like.commoncode.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "cmcode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="code_group")
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractCode extends AuditEntity{
			
	@Id
	@Column(name="code")		
	protected String code;
	
	@Column(name="code_name")
	protected String codeName;
	
	@Column(name="from_dt")
	protected LocalDateTime fromDate;
	
	@Column(name="to_dt")
	protected LocalDateTime toDate;
	
	@Column(name="seq")
	protected Integer sequence;
}
