package com.like.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.todo.domain.model.TaskGroup;
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
		String 	msg = null;		
		boolean isValid = false;
		
		User user = userService.getUser(id);
		
		isValid = validLogin(user, password);
		
		if (isValid) {
			setSessionInfo(session, user);						
			msg = "로그인 처리되었습니다.";
		} else {
			msg = "사용자 정보가 일치하지 않습니다.";
		}
		
		result = WebControllerUtil.getResponse(user,
				 user == null ? 0 : 1, 
				 isValid,
				 msg,
				 HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/user"}, method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> saveTaskGroupList(@RequestBody User user, BindingResult result) {
			
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
	
	private void setSessionInfo(HttpSession session, User user) {
		session.setAttribute("userId", 		user.getUsername());
		session.setAttribute("userName", 	user.getName());
	}
	
	private boolean validLogin(User user, String password) {	
		return user == null ? false : userService.validPassword(user, password);
	}
		
}
