package com.like.code.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commonCodeGroup"})
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
		
	@Column(name="code_name")
	private String codeName;
	
	@Column(name="code_name_abbr")
	private String codeNameAbbreviation;	
	
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
		
	@MapsId("codeGroup")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_group", nullable=false, updatable=false)
	CommonCodeGroup commonCodeGroup;
	
	public void setCommonCodeGroup(CommonCodeGroup commonCodeGroup) {
		this.commonCodeGroup = commonCodeGroup;
	}
	
	protected CommonCode() {}
	
	public CommonCode(CommonCodeId commonCodeId, String codeName, LocalDateTime fromDate, LocalDateTime toDate) {
		this.id = commonCodeId;
		this.codeName = codeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = 0;
		this.useYn = true;
	}
		
	public CommonCode(CommonCodeId id, String codeName, String codeNameAbbreviation, LocalDateTime fromDate,
			LocalDateTime toDate, int seq, boolean useYn, String cmt) {		
		this.id = id;
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = seq;
		this.useYn = useYn;
		this.cmt = cmt;		
	} 
}
