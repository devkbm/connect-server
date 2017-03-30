package com.like.user.domain.repository;

import java.util.List;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;

public interface UserRepository {
	
	User readUser(String userName);
	
	List<Authority> readAuthority(String userName);
	
}
