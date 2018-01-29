package com.like.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.domain.DTOConverter;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.dto.AuthoritySaveDTO;
import com.like.user.domain.repository.dto.LoginRequestDTO;
import com.like.user.domain.repository.dto.PasswordRequestDTO;
import com.like.user.domain.repository.dto.UserSaveDTO;
import com.like.user.service.UserService;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Resource
	UserService userService;
	
	private void authentication(String username, String password, List<GrantedAuthority> authorities, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication); 						
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	}
		 
	@PostMapping(value={"/user/login"})
	public AuthenticationToken login(@RequestBody LoginRequestDTO dto, HttpSession session) {
		
		String username = dto.getUsername();
		String password = dto.getPassword();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));					
		
        authentication(username, password, authorities, session);
        
		User user = userService.getUser(username); 			
		
		return new AuthenticationToken(user.getUsername(),user.getName(), user.getAuthorities(), session.getId());
	}
		
	@GetMapping(value={"/user/{id}/check"})
	public ResponseEntity<?> checkId(@PathVariable(value="id") String userId) {
		
		ResponseEntity<?> result = null;
		
		boolean isDuplication = userService.CheckDuplicationUser(userId);			
		
		result = WebControllerUtil.getResponse(null,
					isDuplication ? 1 : 0, 
					isDuplication ? false : true,
					isDuplication ? "기존 아이디가 존재합니다." : "신규 등록 가능합니다.",
					HttpStatus.OK); 					
		
		return result;
	}
		
	@GetMapping(value={"/user/{id}"})
	public ResponseEntity<?> getUser(@PathVariable(value="id") String userId) {
		
		ResponseEntity<?> result = null;		
		
		User user = userService.getUser(userId);				
		
		result = WebControllerUtil.getResponse(user,
				 user == null ? 0 : 1, 
				 user == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK); 					
		
		return result;
	}
		
	@GetMapping(value={"/user"})
	public ResponseEntity<?> getUserList() {
		
		ResponseEntity<?> result = null;		
		
		List<User> userList = userService.getUserList();				
		
		result = WebControllerUtil.getResponse(userList,
				 userList.size(), 
				 userList.size() > 0 ? true : false ,
				 "조회 되었습니다.",
				 HttpStatus.OK); 					
		
		return result;
	}
	
	@PostMapping(value={"/user/{id}"})
	public ResponseEntity<?> saveUser(@RequestBody UserSaveDTO userDTO, BindingResult result) throws IllegalArgumentException, IllegalAccessException, SecurityException {
			
		ResponseEntity<?> res = null;
		User user = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			user = new User(userDTO.getUserId(), userDTO.getName());			
			
			DTOConverter.convertEntityByAnnotation(user, userDTO);
			
			userService.createUser(user);					
									
			res = WebControllerUtil.getResponse(null,
					1, 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}	
	
	@DeleteMapping(value={"/user/{id}"})
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String userId) {
			
		ResponseEntity<?> res = null;
						
		userService.deleteUser(userId);					
								
		res = WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);		
								 					
		return res;
	}
		
	@PostMapping(value={"/user/{id}/changePassword"})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {
			
		ResponseEntity<?> res = null;
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());					
								
		res = WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 변경되었습니다.", 
				HttpStatus.OK);		
								 					
		return res;
	}
			
	@PostMapping(value={"/user/{id}/initPassword"})
	public ResponseEntity<?> initializePassword(@PathVariable(value="id") String userId) {
			
		ResponseEntity<?> res = null;
				
		userService.initPassword(userId);
													
		res = WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 초기화되었습니다.", 
				HttpStatus.OK);		
								 					
		return res;
	}
	
	@RequestMapping(value={"/authority"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthorityList() {
		
		ResponseEntity<?> result = null;		
		
		List<Authority> authorityList = userService.getAllAuthorities();				
		
		result = WebControllerUtil.getResponse(authorityList,
				authorityList.size(), 
				authorityList.size() > 0 ? true : false ,
				 "조회 되었습니다.",
				 HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/authority/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthority(@PathVariable(value="id") String authorityName) {
		
		ResponseEntity<?> result = null;		
		
		Authority authority = userService.getAuthority(authorityName);				
		
		result = WebControllerUtil.getResponse(authority,
				 authority == null ? 0 : 1, 
				 authority == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/authority"}, method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> saveAuthority(@RequestBody AuthoritySaveDTO authorityDTO, BindingResult result) throws IllegalArgumentException, IllegalAccessException, SecurityException {
			
		ResponseEntity<?> res = null;
		Authority authority = null;
				
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			authority = userService.getAuthority(authorityDTO.getAuthority());
			
			if (authority == null) {
				authority = new Authority(authorityDTO.getAuthority(), authorityDTO.getDescription());
			} else {
				DTOConverter.convertEntityByAnnotation(authority, authorityDTO);
			}
			
			userService.createAuthority(authority);					
									
			res = WebControllerUtil.getResponse(null,
					1, 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}	
			
}
