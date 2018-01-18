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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.todo.domain.model.TaskGroup;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.dto.LoginRequestDTO;
import com.like.user.domain.repository.dto.PasswordRequestDTO;
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
	
	@RequestMapping(value={"/user/login"}, method=RequestMethod.POST) 
	public AuthenticationToken login(@RequestBody LoginRequestDTO dto, HttpSession session) {
		
		String username = dto.getUsername();
		String password = dto.getPassword();

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));					
		
        authentication(username, password, authorities, session);
        
		User user = userService.getUser(username); 			
		
		return new AuthenticationToken(user.getUsername(),user.getName(), user.getAuthorities(), session.getId());
	}
	
	
	@RequestMapping(value={"/user/check/{id}"}, method=RequestMethod.GET) 
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
	
	
	@RequestMapping(value={"/user/{id}"}, method=RequestMethod.GET) 
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
	
	@RequestMapping(value={"/user"}, method=RequestMethod.GET) 
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
	
	@RequestMapping(value={"/user"}, method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> saveUser(@RequestBody User user, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			log.info(user.toString());
			userService.createUser(user);					
									
			res = WebControllerUtil.getResponse(null,
					1, 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}
	
	@RequestMapping(value={"/user/changePassword"}, method={RequestMethod.POST})
	public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDTO dto) {
			
		ResponseEntity<?> res = null;
						
		userService.changePassword(dto.getUserId(), dto.getBeforePassword(), dto.getAfterPassword());					
								
		res = WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);		
								 					
		return res;
	}
	
	@RequestMapping(value={"/user/{id}"}, method={RequestMethod.DELETE})
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
	
	private void setSessionInfo(HttpSession session, User user) {
		session.setAttribute("userId", 		user.getUsername());
		session.setAttribute("userName", 	user.getName());
	}
	
	
	/*@RequestMapping(value={"/auth/login"}, method={RequestMethod.POST})
	public ResponseEntity<?> loing() {													 			
		return null;
	}*/
	
			
}
