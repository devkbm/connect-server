package com.like.commoncode.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.id.CommonCodeId;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class CodeSave implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;

		private String codeGroup;
			
		private String code;
			
		private String codeName;
			
		private String codeNameAbbreviation;		
			
		private LocalDate fromDate;
			
		private LocalDate toDate;
			
		private int seq;
			
		private boolean useYn;
		
		private String cmt;
							
		public Code getCommonCode() {	
						
			return new Code(new CommonCodeId(this.codeGroup, this.code), this.codeName, this.codeNameAbbreviation, 
							this.fromDate.atStartOfDay(), this.toDate.atStartOfDay(),	this.seq, this.useYn, this.cmt);
		}
	}
}
