package com.like.code.domain.repository.dto;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommonCodeComboDTO implements Serializable {
			
	private static final long serialVersionUID = 1396114277976142701L;

	private String code;
		
	private String codeName;
		
	private String codeNameAbbr;

	@QueryProjection
	public CommonCodeComboDTO(String code, String codeName, String codeNameAbbr) {
		super();
		this.code = code;
		this.codeName = codeName;
		this.codeNameAbbr = codeNameAbbr;
	}		
			
}
