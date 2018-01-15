package com.like.user.domain.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken {
	
	private String userId;
	private String userName;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
       
    public AuthenticationToken(String userId, String userName, Collection<? extends GrantedAuthority> collection, String token) {
    	this.userId = userId;
        this.userName = userName;
        this.authorities = collection;
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
    
    public String getToken() {
         return token;
    }
       
}
