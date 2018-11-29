package com.like.menu.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QProgram;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class ProgramQueryDTO implements Serializable {

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
