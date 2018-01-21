package com.like.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "cmauthority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 5255280527856714047L;
	
	@Id
	@Column(name="authority_name")
	String authorityName;
	
	@Column(name="desc")
	String description;
	
	@Override
	public String getAuthority() {
		return this.authorityName;
	}
}
