package com.like.term.web;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.term.domain.model.Term;
import com.like.term.domain.repository.dto.TermQueryDTO;
import com.like.term.service.TermService;

/**
 * 게시판 Rest 컨트롤러
 * 
 * @author 	bmkim
 * @date	2016.2.23
 */
@RestController
public class TermController {

	@Resource
	private TermService termService;
	
	private static final Logger log = LoggerFactory.getLogger(TermController.class);		
	
	@RequestMapping(value={"/common/term"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTerm(@RequestParam(value="id", required=false) Long id) {
			
		ResponseEntity<?> result = null;
		
		List<Term> list; 		
				
		if ( id == null ) {
			list = termService.getTermList();			
		} else {
			list = new ArrayList<Term>();
			Term term = termService.getTerm(id);
			if (term != null) 			
				list.add(termService.getTerm(id));			
		}			
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
	@RequestMapping(value={"/common/terms"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTermList(@ModelAttribute TermQueryDTO termQueryDTO) {
			
		ResponseEntity<?> result = null;
		
		List<Term> list = termService.getTermList(termQueryDTO); 							
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
	@RequestMapping(value={"/common/terms"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTerm(@RequestBody List<Term> termList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		} else {
			
			termService.saveTerm(termList);
									
			res = WebControllerUtil.getResponse(null,
					termList.size(), 
					true, 
					String.format("%d 건 저장되었습니다.", 1), 
					HttpStatus.OK);
		}
								 					
		return res;
	}	
	
	@RequestMapping(value={"/common/terms/{id}"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delTerm(@PathVariable(value="id") Long id) {
			
		ResponseEntity<?> result = null;			
		
		termService.deleteTerm(id);
									
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}		
	
}