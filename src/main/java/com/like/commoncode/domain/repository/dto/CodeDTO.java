package com.like.commoncode.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.id.CommonCodeId;

import lombok.Data;

@Data
public class CodeDTO implements Serializable {

	
	private String codeGroup;
		
	private String code;
		
	private String codeName;
		
	private String codeNameAbbreviation;		
		
	private LocalDateTime fromDate;
		
	private LocalDateTime toDate;
		
	private int seq;
		
	private boolean useYn;
	
	private String cmt;
	
	public Code getCommonCode() {						
		return new Code(new CommonCodeId(this.codeGroup, this.code), this.codeName, this.codeNameAbbreviation, 
						this.fromDate, this.toDate,	this.seq, this.useYn, this.cmt);
	}
}
