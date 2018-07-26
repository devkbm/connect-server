package com.like.user.web.dto;

import java.time.LocalDateTime;

import com.like.user.domain.model.Authority;

import lombok.Data;

@Data
public class AuthoritySaveDTO {

	LocalDateTime createdDt;	
		
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
			
	String authority;
			
	String description;
	
	public Authority createAuthority() {
		return new Authority(this.authority, this.description);
	}
}
