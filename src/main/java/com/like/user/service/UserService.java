package com.like.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		if (user == null) {
			throw new UsernameNotFoundException(userId + " is Not Found");
		}
		
		return user;
	}
	
	/**
	 * 사용자 도메인을 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 도메인
	 */
	public User getUser(String userId) {
		User user = userRepository.getUser(userId);
		
		return user;
	}
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	public List<User> getUserList() {
		return userRepository.getUserList();
	}
	
	/**
	 * 사용자를 생성한다.
	 * @param user	사용자 도메인
	 */
	public void createUser(User user) {
		//String rawPassword = user.getPassword();
		//String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		//user.setPassword(encodedPassword);
		userRepository.createUser(user);
		//userRepository.createAuthority(user);							
	}
	
	/**
	 * 사용자 정보를 삭제한다.
	 * @param userId	사용자 아이디
	 */
	public void deleteUser(String userId) {
		 userRepository.deleteUser(userId);
         userRepository.deleteAuthority(userId);
	}
	
	/**
	 * 패스워드가 맞는지 검증한다.
	 * @param user
	 * @param password
	 * @return 검증 여부
	 */
	public boolean validPassword(User user, String password) {
		
		return user.isVaild(password);		
	}
	
	/**
	 * 사용자 비밀번호를 변경한다.
	 * @param userId
	 * @param beforePassword	변경전 비밀번호
	 * @param afterPassword		변경후 비밀번호
	 */
	public void changePassword(String userId, String beforePassword, String afterPassword) {
		User user = userRepository.getUser(userId);
		
		if (user == null)
			throw new UsernameNotFoundException(userId + " is Not Found");
		
		if ( user.isVaild(beforePassword) ) {
			user.changePassword(afterPassword);
		} 
	}
	
	/**
	 * 사용자의 비밀번호를 초기화한다.
	 * @param userId	사용자 아이디
	 */
	public void initPassword(String userId) {
		User user = userRepository.getUser(userId);
		user.initPassword();		
	}			
	
	/**
	 * 사용자 권한 리스트를 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 권한 리스트
	 */
	public List<Authority> getAuthorities(String userId) {        									
        return userRepository.readAuthority(userId);
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
