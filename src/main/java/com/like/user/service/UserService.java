package com.like.user.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

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
		
	public Collection<GrantedAuthority> getAuthorities(String userName) {
		//Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)userRepository.readAuthority(userName);
        
        //return authorities;
		return null;
	}

	public User getUser(String userId) {
		User user = userRepository.getUser(userId);
		// user.setAuthorities(userMapper.readAuthority(username));
		return user;
	}
	
	public void createUser(User user) {
		String rawPassword = user.getPassword();
		//String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		//user.setPassword(encodedPassword);
		userRepository.createUser(user);
		//userRepository.createAuthority(user);
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
}
