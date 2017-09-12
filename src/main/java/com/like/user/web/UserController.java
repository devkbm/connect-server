package com.like.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

@RestController
public class UserController {

	@Resource
	UserService userService;
	
	@RequestMapping(value={"/user/login"}, method=RequestMethod.GET) 
	public ResponseEntity<?> login(@RequestParam(value="id", required=true) String id,
									@RequestParam(value="pwd", required=true) String password,
									HttpSession session) {
		
		ResponseEntity<?> result = null;			
		User user = userService.getUser(id);
						
		boolean isValid = userService.validPassword(user, password);
		
		if (isValid) {
			setSessionInfo(session, user);
		}		
		
		result = WebControllerUtil.getResponse(null,
				 1, 
				 true,
				 "로그인 처리되었습니다.",
				 HttpStatus.OK); 					
		
		return result;
	}
	
	private void setSessionInfo(HttpSession session, User user) {
		session.setAttribute("userId", 		user.getUsername());
		session.setAttribute("userName", 	user.getName());
	}
}
