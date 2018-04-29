package com.like.menu.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.like.menu.domain.model.Menu;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MenuHierarchyDTO implements Serializable {
		
	private String menuGroupCode;
		
	private String menuCode;
	
	private String menuName;
	
	private String parentMenuCode;
		
	private Long sequence;
		
	private Long level;
				
	private String url;
					
	private boolean expanded;
	
	private boolean selected;
	
	private boolean isLeaf;
	
	private List<MenuHierarchyDTO> children;

	@QueryProjection
	public MenuHierarchyDTO(String menuGroupCode, String menuCode, String menuName, String parentMenuCode,
			Long sequence, Long level, String url, boolean isLeaf) {		
		this.menuGroupCode = menuGroupCode;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.parentMenuCode = parentMenuCode;
		this.sequence = sequence;
		this.level = level;
		this.url = url;
		this.isLeaf = isLeaf;		
		this.expanded = false;
		this.selected = false;
	}
	
	
	
	
}
