package com.like.commoncode.domain.model;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.like.common.domain.AuditEntity;
import com.like.commoncode.domain.model.id.CommonCodeId;

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
@Table(name = "cmcode")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = 1122730947003822818L;
	
	@JsonUnwrapped
	@EmbeddedId		
	CommonCodeId id;
		
	@Column(name="code_name")
	String codeName;
	
	@Column(name="code_name_abbr")
	String codeNameAbbreviation;	
	
	@Column(name="from_dt")
	LocalDateTime fromDate;
	
	@Column(name="to_dt")
	LocalDateTime toDate;
	
	@Builder.Default
	@Column(name="seq")
	int seq = 0;
	
	@Builder.Default
	@Column(name="use_yn")
	boolean useYn = true;
	
	@Column(name="cmt")
	String cmt;
		
	@MapsId("codeGroup")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_group", nullable=false, updatable=false)
	CodeGroup commonCodeGroup;
	
	public void setCommonCodeGroup(CodeGroup commonCodeGroup) {
		this.commonCodeGroup = commonCodeGroup;
	}		
			
	@Builder
	public Code(CommonCodeId id, String codeName, String codeNameAbbreviation, LocalDateTime fromDate,
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

	public CodeGroup getCommonCodeGroup() {
		return commonCodeGroup;
	}

	public CommonCodeId getId() {
		return id;
	} 	
}
