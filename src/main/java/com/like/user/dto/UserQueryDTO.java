package com.like.user.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.user.domain.model.QUser;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class UserQueryDTO implements Serializable {

	private final QUser qUser = QUser.user;
	
	String userId;
	
	String name;
		
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(this.userId)) {
			builder.and(qUser.userId.like("%"+this.userId+"%"));
		}
		
		if (StringUtils.hasText(this.name)) {
			builder.and(qUser.name.like("%"+this.name+"%"));
		}
		
		return builder;
	}
}
