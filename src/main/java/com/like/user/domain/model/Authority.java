package com.like.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "comauthority")
public class Authority extends AuditEntity implements GrantedAuthority {

	private static final long serialVersionUID = 5255280527856714047L;
	
	@Id
	@Column(name="authority_name")
	String authorityName;
	
	@Column(name="description")
	String description;
	
	protected Authority() {}
	
	public Authority(String authorityName, String description) {		
		this.authorityName = authorityName;
		this.description = description;
	}	
	
	@Override
	public String getAuthority() {
		return this.authorityName;
	}

	public String getDescription() {
		return description;
	}

	
	
}
