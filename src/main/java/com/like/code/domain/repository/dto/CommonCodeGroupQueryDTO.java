package com.like.code.domain.repository.dto;

import org.springframework.util.StringUtils;

import com.like.code.domain.model.QCommonCodeGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class CommonCodeGroupQueryDTO {
	
	private final QCommonCodeGroup qCommonCodeGroup = QCommonCodeGroup.commonCodeGroup;
	
	private String codeGroup;
	
	private String codeGroupName;
			
	public BooleanBuilder getQuerySpec() {
		BooleanBuilder builder = new BooleanBuilder();
					
		if (StringUtils.hasText(this.codeGroup))
			builder.and(qCommonCodeGroup.codeGroup.like("%"+codeGroup+"%"));
		
		if (StringUtils.hasText(this.codeGroupName))
			builder.and(qCommonCodeGroup.codeGroupName.like("%"+codeGroupName+"%"));			
		
		return builder;
	}
}
