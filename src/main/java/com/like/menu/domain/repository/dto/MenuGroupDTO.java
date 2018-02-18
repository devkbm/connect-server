package com.like.menu.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.menu.domain.model.MenuGroup;

import lombok.Data;

@Data
public class MenuGroupDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	private String menuGroupCode;
		
	private String menuGroupName;
		
	private String description;		
	
	public MenuGroup setMenuGroup(MenuGroup menuGroup) {
		
		if (menuGroup == null) {
			menuGroup = new MenuGroup(this.menuGroupCode, this.menuGroupName, this.description);
		} else {
			menuGroup.setMenuGroupInfo(this.getMenuGroupName(), this.getDescription());
		}
			
		return menuGroup;
	}
}
