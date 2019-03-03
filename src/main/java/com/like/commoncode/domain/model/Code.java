package com.like.commoncode.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commonCodeGroup"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Builder
@Entity
@Table(name = "comcode")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = 1122730947003822818L;
	
	@Column(name="code_id")
	String id;
		
	@Column(name="p_code")
	String pCode;
	
	@Column(name="code")
	String code;
		
	@Column(name="code_name")
	String codeName;
	
	@Column(name="code_name_abbr")
	String codeNameAbbreviation;	
	
	@Column(name="from_dt")
	LocalDateTime fromDate;
	
	@Column(name="to_dt")
	LocalDateTime toDate;
	
	@Builder.Default
	@Column(name="prt_seq")
	int seq = 0;
	
	@Builder.Default
	@Column(name="use_yn")
	boolean useYn = true;
	
	@Builder.Default
	@Column(name="fixed_length_yn")
	boolean fixedLengthYn = true;
	
	@Column(name="code_length")
	Integer codeLength;
	
	@Column(name="cmt")
	String cmt;
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_code", nullable=false, updatable=false)
	Code parentCode;

	@Builder
	public Code(String id, String pCode, String code, String codeName, String codeNameAbbreviation,
			LocalDateTime fromDate, LocalDateTime toDate, int seq, boolean useYn, boolean fixedLengthYn,
			Integer codeLength, String cmt) {		
		this.id = id;
		this.pCode = pCode;
		this.code = code;
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = seq;
		this.useYn = useYn;
		this.fixedLengthYn = fixedLengthYn;
		this.codeLength = codeLength;
		this.cmt = cmt;
	}
	
					
}
