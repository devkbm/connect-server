package com.like.user.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.like.common.domain.AuditEntity;

import lombok.ToString;

@Entity
@ToString
@Table(name = "cmuser")
public class User extends AuditEntity implements UserDetails {
	
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
				
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="cmuserauthority",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="authority_name"))
	private List<Authority> authorities = new ArrayList<Authority>();
	
	protected User() {}
	
	public User(String userId, String userName) {
		this.userId = userId;
		this.name = userName;
		this.isEnabled = true;
		this.isAccountNonExpired = true;
		this.isAccountNonLocked = true;
		this.isCredentialsNonExpired = true;
	}
	
	@Override
	@JsonIgnore	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public List<Authority> getAuthorityList() {
		return authorities;
	}
	
	@Override
	@JsonProperty("userId")
	public String getUsername() {		
		return userId;
	}

	@Override		
	public String getPassword() {
		return password;
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
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;		
	}
	
	public void addAuthoritiy(Authority authority) {
		this.authorities.add(authority);
	}	
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	/**
	 * 비밀번호를 초기화한다. 
	 * 초기화 비밀번호 : 12345678
	 */
	public void initPassword() {
		this.password = "12345678";	
	}
	
}
