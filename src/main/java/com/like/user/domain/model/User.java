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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.like.common.domain.AuditEntity;
import com.like.menu.domain.model.MenuGroup;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@Builder
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"authorities","comusermenugroup"})
@Entity
@Table(name = "comuser")
public class User extends AuditEntity implements UserDetails {
	
	private static final long serialVersionUID = 2601682947639908458L;

	@Id	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)	
	@Column(name="pwd")
	private String password;	
	
	@Builder.Default
	@Column(name="non_expired_yn")
	private Boolean isAccountNonExpired = true;
	
	@Builder.Default
	@Column(name="non_locked_yn")
	private Boolean isAccountNonLocked = true;
	
	@Builder.Default
	@Column(name="pass_non_expired_yn")
	private Boolean isCredentialsNonExpired = true;
	
	@Builder.Default
	@Column(name="enabled_yn")
	private Boolean isEnabled = true;
	
	@Singular(value="authorities")
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comuserauthority",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="authority_name"))	
	private List<Authority> authorities;
		
	@Singular(value="menuGroupList") 
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comusermenugroup",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="menu_group_code"))	
	private List<MenuGroup> menuGroupList;			
		
	public User(String userId, String name, String password, Boolean isAccountNonExpired, Boolean isAccountNonLocked,
			Boolean isCredentialsNonExpired, Boolean isEnabled, List<Authority> authorities,
			List<MenuGroup> menuGroupList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
	}	
	
	@Override
	@JsonIgnore	
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
	
	public List<Authority> getAuthorityList() {
		return authorities;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;		
	}
	
	public void addAuthoritiy(Authority authority) {
		this.authorities.add(authority);
	}	
			
	public List<MenuGroup> getMenuGroupList() {
		return menuGroupList;
	}
			
	public void getMenuGroupList(List<MenuGroup> menuGroupList) {
		this.menuGroupList = menuGroupList;
	}
	
	public void setMenuGroupList(List<MenuGroup> menuGroupList) {
		this.menuGroupList = menuGroupList;
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
