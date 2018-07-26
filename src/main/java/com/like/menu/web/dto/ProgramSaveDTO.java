package com.like.menu.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.menu.domain.model.MenuGroup;

import lombok.Data;

@Data
public class ProgramSaveDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	@NotEmpty
	String programCode;
		
	@NotEmpty
	String programName; 
			
	@NotEmpty
	String url;
		
	String description;		
	
}
