package com.like.user.domain.repository.dto;

import java.time.LocalDateTime;

import com.like.common.domain.annotation.DtoField;
import com.like.user.domain.model.Authority;

import lombok.Data;

@Data
public class AuthoritySaveDTO {

	LocalDateTime createdDt;	
		
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
		
	@DtoField(targetEntity=Authority.class, fieldName="authorityName")
	String authority;
		
	@DtoField(targetEntity=Authority.class, fieldName="description")
	String description;
	
	public Authority createAuthority() {
		return new Authority(this.authority, this.description);
	}
}
