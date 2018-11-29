package com.like.menu.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class MenuQueryDTO implements Serializable {

	private final QMenu qMenu = QMenu.menu;
	
	String menuCode;
	
	String menuName;
			
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(this.menuCode)) {
			builder.and(qMenu.menuCode.like("%"+this.menuCode+"%"));
		}
		
		if (StringUtils.hasText(this.menuName)) {
			builder.and(qMenu.menuName.like("%"+this.menuName+"%"));
		}			
		
		return builder;
	}
}
