package com.like.commoncode.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {

	@Data
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	public static class CodeSave implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String id;
		
		String parentId;
			
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;		
		
		boolean useYn;
		
		LocalDate fromDate;
			
		LocalDate toDate;			
		
		int seq;
			
		boolean fixedLengthYn;
		
		Integer codeLength;
		
		String cmt;		
	}
}
