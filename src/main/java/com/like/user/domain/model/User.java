package com.like.user.domain.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@ToString
@Table(name = "cmuser")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 2601682947639908458L;

	@Id	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name="password")
	private String password;	
	
	@Column(name="non_expired_yn")
	private Boolean isAccountNonExpired;
	
	@Column(name="non_locked_yn")
	private Boolean isAccountNonLocked;
	
	@Column(name="pass_non_expried_yn")
	private Boolean isCredentialsNonExpired;
	
	@Column(name="enabled_yn")
	private Boolean isEnabled;
		
	@Transient
	@JsonIgnore
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
	@JsonProperty("userId")
	public String getUsername() {
		return userId;
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
			
	public String getName() {
		return name;
	}
	
	public boolean isVaild(String password) {
		return this.password.equals(password) ? true : false;
	}
	
	
	
}
