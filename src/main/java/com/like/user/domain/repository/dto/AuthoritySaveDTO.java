package com.like.user.domain.repository.dto;

import com.like.common.domain.annotation.DTOInfo;
import com.like.user.domain.model.Authority;

import lombok.Data;

@Data
public class AuthoritySaveDTO {

	@DTOInfo(classInstance=Authority.class, fieldName="authorityName")
	String authority;
		
	@DTOInfo(classInstance=Authority.class, fieldName="description")
	String description;
}
