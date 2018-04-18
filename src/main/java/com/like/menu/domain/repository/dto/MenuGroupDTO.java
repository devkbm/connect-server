package com.like.menu.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.common.dto.annotation.DtoField;
import com.like.menu.domain.model.MenuGroup;

import lombok.Data;

@Data
public class MenuGroupDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	@NotEmpty	
	private String menuGroupCode;
	
	@NotEmpty	
	private String menuGroupName;
			
	private String description;		
		
}
