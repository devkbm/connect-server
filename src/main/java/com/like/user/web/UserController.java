package com.like.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.service.MenuQueryService;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;
import com.like.user.web.dto.AuthoritySaveDTO;
import com.like.user.web.dto.LoginRequestDTO;
import com.like.user.web.dto.PasswordRequestDTO;
import com.like.user.web.dto.UserSaveDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Resource
	UserService userService;
	
	@Resource
	MenuQueryService menuQueryService;
	
	private void authentication(String username, String password, List<GrantedAuthority> authorities, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication); 						
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	}
		 
	@PostMapping(value={"/user/login"})
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpSession session, BindingResult result) {
		
		if ( result.hasErrors() ) {
			log.info(result.toString());
			return null;
		}
		
		String username = dto.getUsername();
		String password = dto.getPassword();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));					
		
        authentication(username, password, authorities, session);
        
		User user = userService.getUser(username); 			
		
		return new AuthenticationToken(user.getUsername(),user.getName(), user.getAuthorities(), user.getMenuGroupList(), session.getId());
	}
		
	@GetMapping(value={"/user/{id}/check"})
	public ResponseEntity<?> checkId(@PathVariable(value="id") String userId) {
						
		boolean isDuplicated = userService.CheckDuplicationUser(userId);					
				
		return WebControllerUtil.getResponse(null,
				isDuplicated ? 1 : 0, 
				isDuplicated ? false : true,
				isDuplicated ? "기존 아이디가 존재합니다." : "신규 등록 가능합니다.",
				HttpStatus.OK); 
	}
		
	@GetMapping(value={"/user/{id}"})
	public ResponseEntity<?> getUser(@PathVariable(value="id") String userId) {
						
		User user = userService.getUser(userId);				
				
		UserSaveDTO dto = new UserSaveDTO(user);					
		
		return WebControllerUtil.getResponse(dto,
				 user == null ? 0 : 1, 
				 user == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK);
	}
		
	@GetMapping(value={"/user"})
	public ResponseEntity<?> getUserList() {
				
		List<User> userList = userService.getUserList();						 				
		
		return WebControllerUtil.getResponse(userList,
				userList.size(), 
				userList.size() > 0 ? true : false ,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@PostMapping(value={"/user/{id}"})	
	public ResponseEntity<?> saveUser(@RequestBody UserSaveDTO userDTO, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}							
	
		User user = new User(userDTO.getUserId(), userDTO.getName(), userDTO.getPassword(), 
					//	userDTO.getAccountNonExpired(), userDTO.getAccountNonLocked(), userDTO.getCredentialsNonExpired(),
						true, true, true,
						userDTO.getEnabled());					
		
		List<Authority> authList = userService.getAllAuthorityList(userDTO.getAuthorityList());
					
		List<MenuGroup> menuGroupList = menuQueryService.getMenuGroupList(userDTO.getMenuGroupList());
		
		user.setAuthorities(authList);
		user.setMenuGroupList(menuGroupList);
		
		userService.createUser(user);					
																					 		
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	@DeleteMapping(value={"/user/{id}"})
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") String userId) {
										
		userService.deleteUser(userId);															
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@PostMapping(value={"/user/{id}/changePassword"})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {				
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());													
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 변경되었습니다.", 
				HttpStatus.OK);
	}
			
	@PostMapping(value={"/user/{id}/initPassword"})
	public ResponseEntity<?> initializePassword(@PathVariable(value="id") String userId) {			
				
		userService.initPassword(userId);														
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				"비밀번호가 초기화되었습니다.", 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/authority"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthorityList() {				
		
		List<Authority> authorityList = userService.getAllAuthorities();								 				
		
		return WebControllerUtil.getResponse(authorityList,				
				authorityList.size(), 
				authorityList.size() > 0 ? true : false ,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/authority/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthority(@PathVariable(value="id") String authorityName) {			
		
		Authority authority = userService.getAuthority(authorityName);										
		
		return WebControllerUtil.getResponse(authority,
				 authority == null ? 0 : 1, 
				 authority == null ? false : true,
				 "조회 되었습니다.",
				 HttpStatus.OK);
	}
	
	@RequestMapping(value={"/authority"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveAuthority(@RequestBody AuthoritySaveDTO dto, BindingResult result) throws IllegalArgumentException, IllegalAccessException, SecurityException, InstantiationException {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}
		
		Authority authority = userService.getAuthority(dto.getAuthority());
		
		if (authority == null) {
			authority = new Authority(dto.getAuthority(), dto.getDescription());
		} 
		
		userService.createAuthority(authority);					
																				 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	@DeleteMapping("/authority/{id}")
	public ResponseEntity<?> deleteAuthority(@PathVariable(value="id") String authorityName) {
		
		userService.deleteAuthority(authorityName);					
			
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
			
}
