package com.like.commoncode.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.commoncode.domain.model.QCodeGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class CodeGroupDTO {

	@Data
	public static class QueryCondition implements Serializable {
		
		private final QCodeGroup qCodeGroup = QCodeGroup.codeGroup1;
		
		private String codeGroup;
		
		private String codeGroupName;
				
		public BooleanBuilder getQuerySpec() {
			BooleanBuilder builder = new BooleanBuilder();
						
			if (StringUtils.hasText(this.codeGroup))
				builder.and(qCodeGroup.codeGroup.like("%"+codeGroup+"%"));
			
			if (StringUtils.hasText(this.codeGroupName))
				builder.and(qCodeGroup.codeGroupName.like("%"+codeGroupName+"%"));			
			
			return builder;
		}
	}
	
	
}
