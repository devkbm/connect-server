package com.like.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority {

	@Id
	@Column(name="username")
	String userName;
	
	@Column(name="authority_name")
	String authorityName;
}
