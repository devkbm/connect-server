package com.like.commoncode.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
		
	@GetMapping("/common/codegroups/codes")
	public ResponseEntity<?> getCodes(@RequestParam(value="codeGroup", required=true) String codeGroup,
			@RequestParam(value="qType", required=false) String qType) {
					
		List<?> list = null;			
		
		if (StringUtils.hasText(qType)) { 
			if (qType.equals("combo")) 
				list = commonCodeQueryService.getCodeListByComboBox(codeGroup);			
		} else {
			list = commonCodeQueryService.getCodeList(codeGroup);
		}
					 					
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					

	}
	
	
	@RequestMapping(value={"/common/codegroup/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.CodeSave dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Code parentCode = null;
		
		if ( dto.getParentId() != null ) {
			commonCodeQueryService.getCode(dto.getParentId());
		}				
		
		Code code = CodeDTOAssembler.createEntity(dto, parentCode);
				
		commonCodeCommandService.saveCode(code);		
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}	
		
	@DeleteMapping("/common/codegroups/codes")
	public ResponseEntity<?> delCode(@RequestParam(value="codeGroup", required=true) String codeGroup,
			@RequestParam(value="code", required=true) String code) {						
												
		commonCodeCommandService.deleteCode(code);
								 						
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
}
