package com.like.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.web.BoardController;
import com.like.common.web.util.WebControllerUtil;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	UserService userService;
	
	@RequestMapping(value={"/user/login"}, method=RequestMethod.GET) 
	public ResponseEntity<?> login(@RequestParam(value="id", required=true) String id,
									@RequestParam(value="pwd", required=true) String password,
									HttpSession session) {
		
		ResponseEntity<?> result = null;
		String 	msg;
		boolean isSuccess = false;
		boolean isValid = false;
		
		User user = userService.getUser(id);
		
		if (user == null) {			
			isValid = false;						
		} else {
			isValid = userService.validPassword(user, password);
		}
		
		if (isValid) {
			setSessionInfo(session, user);			
			msg = "로그인 처리되었습니다.";
			isSuccess = true;
		} else {
			msg = "사용자 정보가 일치하지 않습니다.";
			isSuccess = false;
		}
		
		result = WebControllerUtil.getResponse(null,
				 0, 
				 isSuccess,
				 msg,
				 HttpStatus.OK); 					
		
		return result;
	}
	
	private void setSessionInfo(HttpSession session, User user) {
		session.setAttribute("userId", 		user.getUsername());
		session.setAttribute("userName", 	user.getName());
	}
		
}
