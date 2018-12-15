package com.like.menu.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QProgram;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class ProgramDTO {

	@Data
	public static class QueryCondition implements Serializable {
		
		private static final long serialVersionUID = 698694617356322910L;

		private final QProgram qProgram = QProgram.program;
		
		String programCode;
		
		String programName;
		
		String url;
		
		String description;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			if (StringUtils.hasText(this.programCode)) {
				builder.and(qProgram.programCode.like("%"+this.programCode+"%"));
			}
			
			if (StringUtils.hasText(this.programName)) {
				builder.and(qProgram.programName.like("%"+this.programName+"%"));
			}
			
			if (StringUtils.hasText(this.url)) {
				builder.and(qProgram.url.like("%"+this.url+"%"));
			}
			
			if (StringUtils.hasText(this.description)) {
				builder.and(qProgram.description.like("%"+this.description+"%"));
			}
			
			return builder;
		}
	}
	
	@Data
	public static class ProgramSave implements Serializable {
				
		private static final long serialVersionUID = -1400051159309726788L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		String programCode;
			
		@NotEmpty
		String programName; 
				
		@NotEmpty
		String url;
			
		String description;		
		
	}
}
