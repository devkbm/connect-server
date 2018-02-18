package com.like.user.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.like.menu.domain.model.MenuGroup;

public class AuthenticationToken {
	
	private String userId;
	private String userName;
    private Collection<? extends GrantedAuthority> authorities;
    private List<MenuGroup> menuGroupList;
    private String token;
       
    public AuthenticationToken(
    		String userId, 
    		String userName, 
    		Collection<? extends GrantedAuthority> collection,
    		List<MenuGroup> menuGroupList,
    		String token) {
    	this.userId = userId;
        this.userName = userName;
        this.authorities = collection;
        this.menuGroupList = menuGroupList;
        this.token = token;
    }
    
    public String getUserId() {
    	return userId;
    }
    
    public String getUsername() {
         return userName;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return authorities;
    }
    
    public List<MenuGroup> getMenuGroupList() {
    	return this.menuGroupList;    	
    }
    
    public String getToken() {
         return token;
    }
       
}
