package com.like.dept.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.dept.domain.model.Dept;
import com.like.dept.service.DeptService;

@RestController
public class DeptController {

	@Resource(name = "deptService")
	private DeptService deptService;
	
	private static final Logger log = LoggerFactory.getLogger(DeptController.class);
	
	@RequestMapping(value={"/common/dept"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getDeptList(@RequestParam(value="deptcd", required=false) String deptcd) {
			
		ResponseEntity<?> result = null;
		
		List<Dept> list = deptService.getDeptList();  						 
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	
}
