package com.like.user.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.user.domain.model.QAuthority;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class AuthorityQueryDTO implements Serializable {

	private final QAuthority qAuthority = QAuthority.authority;
	
	String authority;
		
	String description;
		
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(this.authority)) {
			builder.and(qAuthority.authorityName.like("%"+this.authority+"%"));
		}
		
		if (StringUtils.hasText(this.description)) {
			builder.and(qAuthority.description.like("%"+this.description+"%"));
		}
		
		return builder;
	}
	
}
