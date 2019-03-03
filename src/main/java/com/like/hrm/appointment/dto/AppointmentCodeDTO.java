package com.like.hrm.appointment.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.dto.CodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentCodeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class CodeSave implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
		
			
		private String code;
			
		private String codeName;					
			
		private LocalDate fromDate;
			
		private LocalDate toDate;
			
		private int seq;
			
		private boolean useYn;
		
		private String cmt;
							
		public AppointmentCode getCommonCode() {	
						
			return new AppointmentCode(this.code, this.codeName, this.fromDate.atStartOfDay(), this.toDate.atStartOfDay());
		}
	}
}
