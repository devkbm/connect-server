package com.like.dept.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.dept.domain.model.Dept;
import com.like.dept.dto.DeptDTO;
import com.like.dept.service.DeptService;

@RestController
public class DeptController {

	@Resource(name = "deptService")
	private DeptService deptService;
		
	@RequestMapping(value={"/common/dept"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getDeptList(@ModelAttribute DeptDTO.SearchCondition searchCondition) {
							
		List<Dept> list = deptService.getDeptList(searchCondition);  						 						
		
		return WebControllerUtil.getResponse(list, 
											list.size(), 
											true, 
											String.format("%d 건 조회되었습니다.", list.size()), 
											HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/dept"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody DeptDTO.DeptSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 					
				
		deptService.saveDept(dto);		
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	
}
