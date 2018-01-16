package com.like.user.domain.repository;

import java.util.List;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;

public interface UserRepository {
			
	User getUser(String userId);
	
	List<User> getUserList();
	
	List<Authority> readAuthority(String userId);
	
	void createUser(User user);
	
	void createAuthority(Authority user);
	
	void deleteUser(String userId);
	
	void deleteAuthority(String userId);
}
