package com.like.menu.web.dto;

import java.io.Serializable;
import java.util.List;

import com.like.menu.domain.model.enums.MenuType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MenuHierarchyDTO implements Serializable {
		
	private String menuGroupCode;
		
	private String key;
	
	private String title;
	
	private String parentMenuCode;
		
	private String menuType;
	
	private Long sequence;
		
	private Long level;
				
	private String url;
					
	private boolean expanded;
	
	private boolean selected;
	
	private boolean isLeaf;
	
	private List<MenuHierarchyDTO> children;

	@QueryProjection
	public MenuHierarchyDTO(String menuGroupCode, String key, String title, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url, boolean isLeaf) {		
		this.menuGroupCode = menuGroupCode;
		this.key = key;
		this.title = title;
		this.parentMenuCode = parentMenuCode;
		this.menuType = menuType.toString();
		this.sequence = sequence;
		this.level = level;
		this.url = url;
		this.isLeaf = isLeaf;		
		this.expanded = false;
		this.selected = false;
	}
	
	
	
	
}
