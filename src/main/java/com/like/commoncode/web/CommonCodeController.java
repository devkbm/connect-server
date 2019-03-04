package com.like.commoncode.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeDTOAssembler;
import com.like.commoncode.dto.CodeDTO;
import com.like.commoncode.service.CommonCodeCommandService;
import com.like.commoncode.service.CommonCodeQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommonCodeController {

	@Resource
	private CommonCodeCommandService commonCodeCommandService;
	
	@Resource
	private CommonCodeQueryService commonCodeQueryService;	
	
		
	@GetMapping("/common/code")
	public ResponseEntity<?> getCodes(@RequestParam(value="parentId", required=false) String parentId) {
							
		List<Code> list = null; 
		
		if ( parentId == null ) {			
			list = commonCodeQueryService.getAllCodeList();
		} else {
			list = commonCodeQueryService.getCodeList(parentId);
		}			
					 					
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					

	}
	
	
	@RequestMapping(value={"/common/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.CodeSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Code parentCode = null;
		
		if ( dto.getParentId() != null ) {
			parentCode = commonCodeQueryService.getCode(dto.getParentId());
		}				
		
		Code code = CodeDTOAssembler.createEntity(dto, parentCode);
				
		commonCodeCommandService.saveCode(code);		
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
		
	@DeleteMapping("/common/code/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		commonCodeCommandService.deleteCode(id);
								 						
		return WebControllerUtil.getResponse(null, 
											1, 
											true, 
											String.format("%d 건 삭제되었습니다.", 1), 
											HttpStatus.OK);
	}
	
	
}
