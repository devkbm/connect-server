package com.like.menu.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenuGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class MenuGroupQueryDTO implements Serializable {

	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
	
	String menuGroupCode;
	
	String menuGroupName;
			
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(this.menuGroupCode)) {
			builder.and(qMenuGroup.menuGroupCode.like("%"+this.menuGroupCode+"%"));
		}
		
		if (StringUtils.hasText(this.menuGroupName)) {
			builder.and(qMenuGroup.menuGroupName.like("%"+this.menuGroupName+"%"));
		}			
		
		return builder;
	}
}
