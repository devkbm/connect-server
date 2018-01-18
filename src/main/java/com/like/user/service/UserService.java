package com.like.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
						
		User user = userRepository.getUser(userId);
		
		return user;
	}
	
	public boolean validPassword(User user, String password) {
		
		return user.isVaild(password);		
	}
		
	public List<Authority> getAuthorities(String userName) {        									
        return userRepository.readAuthority(userName);
	}

	public User getUser(String userId) {
		User user = userRepository.getUser(userId);
		
		return user;
	}
	
	public void createUser(User user) {
		String rawPassword = user.getPassword();
		//String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		//user.setPassword(encodedPassword);
		userRepository.createUser(user);
		//userRepository.createAuthority(user);			
	}
	
	public void changePassword(String userId, String beforePassword, String afterPassword) {
		User user = userRepository.getUser(userId);
		
		if ( user.isVaild(beforePassword) ) { 		
			user.changePassword(afterPassword);
		} else {
			// 익셉션 발생
		}
	}
	
	public void deleteUser(String userId) {
		 userRepository.deleteUser(userId);
         userRepository.deleteAuthority(userId);
	}
	
	public PasswordEncoder passwordEncoder(){
		return this.passwordEncoder;
	}
	
	
	/**
	 * 중복 유저 검증 기능
	 * @param userId
	 * @return 기존 아이디가 있으면 true, 아니면 false 리턴
	 */
	public boolean CheckDuplicationUser(String userId) {
		User user = userRepository.getUser(userId);
		
		return user != null ? true : false; 
	}
	
	public List<User> getUserList() {
		return userRepository.getUserList();
	}
}
