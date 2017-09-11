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
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.getUser(userName);
		
		return user;
	}
	
	public Collection<GrantedAuthority> getAuthorities(String userName) {
		//Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)userRepository.readAuthority(userName);
        
        //return authorities;
		return null;
	}

	public User readUser(String userName) {
		User user = userRepository.getUser(userName);
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
	
	public void deleteUser(String userName) {
		 userRepository.deleteUser(userName);
         userRepository.deleteAuthority(userName);
	}
	
	public PasswordEncoder passwordEncoder(){
		return this.passwordEncoder;
	}
}
