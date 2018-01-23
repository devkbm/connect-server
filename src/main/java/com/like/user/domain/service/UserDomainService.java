package com.like.user.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;

public class UserDomainService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		List<Authority> authorities = new ArrayList<Authority>();   		
        authorities.add(new Authority("ROLE_USER","기본 로그인 권한"));
				
		user.setAuthorities(authorities);
		userRepository.saveUser(user);
	}
		
}
