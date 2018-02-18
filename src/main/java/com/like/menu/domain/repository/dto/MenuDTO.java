package com.like.menu.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.id.MenuId;

import lombok.Data;

@Data
public class MenuDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	private String menuGroupCode;
		
	private String menuCode;
	
	private String menuName;
	
	private String parentMenuCode;
		
	private Long sequence;
		
	private Long level;
				
	private String url;
					
	public Menu setMenu(Menu menu) {		
		if (menu == null) {
			menu = new Menu(new MenuId(this.getMenuGroupCode(), this.getMenuCode()),this.getMenuName(), null);
		} else {
			menu.setMenuInfo(this.getMenuName(), this.getParentMenuCode(), this.getSequence(), this.getLevel(), this.getUrl());
		}
			
		return menu;
	}
}
