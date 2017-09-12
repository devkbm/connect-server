package com.like.user.web;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.user.service.UserService;

@RestController
public class UserController {

	@Resource
	UserService userService;
	
	@RequestMapping(value={"/user/login"}, method=RequestMethod.GET) 
	public ResponseEntity<?> login(@RequestParam(value="id", required=true) String id,
									@RequestParam(value="pwd", required=true) String password) {
		
		return null;
	}
}
