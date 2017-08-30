package com.like.todo.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.like.todo.service.TaskQueryService;

@RestController
public class TaskController {

	@Resource
	private TaskQueryService taskQueryService;

	
}
