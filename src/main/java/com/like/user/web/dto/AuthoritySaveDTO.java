package com.like.user.web.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotBlank;

import com.like.user.domain.model.Authority;

import lombok.Data;

@Data
public class AuthoritySaveDTO {

	LocalDateTime createdDt;	
		
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
			
	@NotBlank(message="권한은 필수 항목입니다.")
	String authority;
			
	String description;
	
	public Authority createAuthority() {
		return new Authority(this.authority, this.description);
	}
}
