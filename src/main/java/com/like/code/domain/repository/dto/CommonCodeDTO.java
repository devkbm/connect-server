package com.like.code.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.id.CommonCodeId;

import lombok.Data;

@Data
public class CommonCodeDTO implements Serializable {

	
	private String codeGroup;
		
	private String code;
		
	private String codeName;
		
	private String codeNameAbbr;		
		
	private LocalDateTime fromDate;
		
	private LocalDateTime toDate;
		
	private int seq;
		
	private boolean useYn;
	
	private String cmt;
	
	public CommonCode getCommonCode() {						
		return new CommonCode(new CommonCodeId(this.codeGroup, this.code), this.codeName, this.codeNameAbbr, 
						this.fromDate, this.toDate,	this.seq, this.useYn, this.cmt);
	}
}
