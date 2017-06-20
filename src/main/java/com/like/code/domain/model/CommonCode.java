package com.like.code.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.code.domain.model.id.CommonCodeId;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "cmcode")
@EntityListeners(AuditingEntityListener.class)
public class CommonCode extends AuditEntity implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1122730947003822818L;

	@EmbeddedId		
	private CommonCodeId id;
	
	/*@Column(name="code")
	private String code;*/
	
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
	
	/*@MapsId("codeGroup")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_group", nullable=false, updatable=false)*/
	@MapsId("codeGroup")
	@ManyToOne
	@JoinColumn(name = "code_group")
	CommonCodeGroup codeGroup;
	
	public void setCommonCodeGroup(CommonCodeGroup commonCodeGroup) {
		this.codeGroup = commonCodeGroup;
	}
	
	protected CommonCode() {}
	
	public CommonCode(CommonCodeId commonCodeId, String codeName, LocalDateTime fromDate, LocalDateTime toDate) {
		this.id = commonCodeId;
		this.codeName = codeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
}
