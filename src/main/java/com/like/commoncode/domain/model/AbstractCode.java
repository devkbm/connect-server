package com.like.commoncode.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.like.common.domain.AuditEntity;
import com.like.commoncode.domain.model.id.CommonCodeId;

@Entity
@Table(name = "cmcode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="code_group")
//@DiscriminatorValue("TEST")
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractCode extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -3004490378689231163L;
	
	@JsonUnwrapped
	@EmbeddedId		
	private CommonCodeId id;
	
	@Column(name="code_name")
	private String codeName;
	
	@Column(name="from_dt")
	private LocalDateTime fromDate;
	
	@Column(name="to_dt")
	private LocalDateTime toDate;
	
}
