package com.like.user.domain.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Setter;

@Entity
@Setter
@Table(name = "user")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 2601682947639908458L;

	@Id	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="isaccountnonexpired")
	private boolean isAccountNonExpired;
	
	@Column(name="isaccountnonlocked")
	private boolean isAccountNonLocked;
	
	@Column(name="iscredentialsnonexpired")
	private boolean isCredentialsNonExpired;
	
	@Column(name="isenabled")
	private boolean isEnabled;

	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
}
