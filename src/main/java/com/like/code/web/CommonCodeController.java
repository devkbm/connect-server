package com.like.code.web;

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

import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.service.CommonCodeService;
import com.like.common.web.util.WebControllerUtil;

@RestController
public class CommonCodeController {

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	private static final Logger log = LoggerFactory.getLogger(CommonCodeController.class);
	
	@RequestMapping(value={"/common/codegroups"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCodeGroups(@RequestParam(value="id", required=false) Long id) {
			
		ResponseEntity<?> result = null;
		
		List<CommonCodeGroup> list = new ArrayList<>(); 		
		
		list.add(commonCodeService.getCodeGroup(id)); 			
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}	
}
