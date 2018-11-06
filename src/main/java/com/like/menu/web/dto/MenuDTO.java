package com.like.menu.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.Program;

import lombok.Data;

@Data
public class MenuDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	@NotEmpty
	private String menuGroupCode;
			
	@NotEmpty
	private String menuCode;
		
	@NotEmpty
	private String menuName;
		
	private String parentMenuCode;
	
	private String menuType;
		
	private long sequence;
			
	private long level;
	
	private String program;
						
	public MenuDTO() {}
	
	public MenuDTO(Menu menu) {					
		this.createdDt 		= menu.getCreatedDt();
		this.createdBy 		= menu.getCreatedBy();
		this.modifiedDt 	= menu.getModifiedDt();
		this.modifiedBy 	= menu.getModifiedBy();
		this.menuGroupCode 	= menu.getMenuGroup().getMenuGroupCode();
		this.menuCode 		= menu.getMenuCode();
		this.menuName 		= menu.getMenuName();
		this.parentMenuCode = menu.getParentMenuCode();
		this.menuType		= menu.getMenuType().toString();				
		this.sequence 		= menu.getSequence();
		this.level 			= menu.getLevel();		
		//Optional<Program> program = menu.getProgram()
		this.program 		= menu.getProgram() == null ? "" : menu.getProgram().getProgramCode();
	}		
	
}
