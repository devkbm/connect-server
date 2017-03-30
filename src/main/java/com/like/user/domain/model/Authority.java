package com.like.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

	@Id
	@Column(name="username")
	String userName;
	
	@Column(name="authority_name")
	String authorityName;

	@Override
	public String getAuthority() {
		return this.userName;
	}
}
