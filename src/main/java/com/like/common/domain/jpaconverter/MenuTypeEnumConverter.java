package com.like.common.domain.jpaconverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.like.menu.domain.model.enums.MenuType;

@Converter(autoApply = true)
public class MenuTypeEnumConverter implements AttributeConverter <MenuType, String> {

	@Override
	public String convertToDatabaseColumn(MenuType attribute) {
		return attribute.getCode();
	}	
	
	@Override
	public MenuType convertToEntityAttribute(String dbData) {
		
		String menuitem	= MenuType.MENUITEM.getCode();
		String folder 	= MenuType.FOLDER.getCode();
		String group	= MenuType.GROUP.getCode();
					
		if ( dbData.equals(menuitem) ) {
			return MenuType.MENUITEM;
		} else if ( dbData.equals(folder) ) {
			return MenuType.FOLDER;
		} else if ( dbData.equals(group) ) {
			return MenuType.GROUP;
		}				
		
		return null;
	}



}
