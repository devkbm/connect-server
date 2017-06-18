package com.like.code.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "cmcode")
@EntityListeners(AuditingEntityListener.class)
public class CommonCode extends AuditEntity implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1122730947003822818L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_code")
	private Long pkCode;
	
	@Column(name="code")
	private String code;
	
	@Column(name="code_name")
	private String codeName;
	
	@Column(name="code_name_abbr")
	private String codeNameAbbr;		
	
	@Column(name="from_dt")
	private LocalDateTime fromDate;
	
	@Column(name="to_dt")
	private LocalDateTime toDate;
	
	@Column(name="seq")
	private int seq;
	
	@Column(name="use_yn")
	private boolean useYn;
	
	@Column(name="cmt")
	private String cmt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_code_group", nullable=false, updatable=false)
	CommonCodeGroup codeGroup;
	
	public void setCommonCodeGroup(CommonCodeGroup commonCodeGroup) {
		this.codeGroup = commonCodeGroup;
	}
			
}
