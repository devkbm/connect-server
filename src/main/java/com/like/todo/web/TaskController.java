package com.like.todo.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.service.TaskQueryService;

@RestController
public class TaskController {

	@Resource
	private TaskQueryService taskQueryService;

	@RequestMapping(value={"/todo/taskgroups"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTermList(@RequestParam(value="userId", required=true) String userId) {
			
		ResponseEntity<?> result = null;
		
		List<TaskGroup> list = taskQueryService.getTaskGroupList(userId); 							
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}	
}
