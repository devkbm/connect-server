package com.like.commoncode.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.domain.repository.dto.CodeDTO;
import com.like.commoncode.domain.repository.dto.CodeGroupQueryDTO;
import com.like.commoncode.service.CommonCodeCommandService;
import com.like.commoncode.service.CommonCodeQueryService;

@RestController
public class CommonCodeController {

	@Resource
	private CommonCodeCommandService commonCodeCommandService;
	
	@Resource
	private CommonCodeQueryService commonCodeQueryService;
	
	private static final Logger log = LoggerFactory.getLogger(CommonCodeController.class);
	
	@RequestMapping(value={"/common/codegroups"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCodeGroups(@ModelAttribute CodeGroupQueryDTO commonCodeGroupQueryDTO) {
			
		ResponseEntity<?> result = null;
		
		List<CodeGroup> list = commonCodeQueryService.getCodeGroupList(commonCodeGroupQueryDTO); 		
				
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/common/codegroups"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCodeGroup(@RequestBody List<CodeGroup> codeGroupList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		} 
															
		for (CodeGroup codeGroup : codeGroupList ) {
			commonCodeCommandService.saveCodeGroup(codeGroup);
		}
			
		res = WebControllerUtil.getResponse(null,
				codeGroupList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", codeGroupList.size()), 
				HttpStatus.OK);
		
								 					
		return res;
	}
	
	@RequestMapping(value={"/common/codegroups"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delCodeGroup(@RequestParam(value="codeGroup", required=true) String codeGroup) {
			
		ResponseEntity<?> result = null;			
												
		commonCodeCommandService.deleteCodeGroup(codeGroup);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/common/codegroups/codes"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCodes(@RequestParam(value="codeGroup", required=true) String codeGroup,
			@RequestParam(value="qType", required=false) String qType) {
			
		ResponseEntity<?> result = null;
		List<?> list = null;			
		
		if (StringUtils.hasText(qType)) { 
			if (qType.equals("combo")) 
				list = commonCodeQueryService.getCodeListByComboBox(codeGroup);			
		} else {
			list = commonCodeQueryService.getCodeList(codeGroup);
		}
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/common/codegroups/codes"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody List<CodeDTO> codeList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		for (CodeDTO code : codeList ) {
			commonCodeCommandService.saveCode(code.getCommonCode());
		}
			
		res = WebControllerUtil.getResponse(null,
				codeList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", codeList.size()), 
				HttpStatus.OK);
		
								 					
		return res;
	}
	
	@RequestMapping(value={"/common/codegroups/codes"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delCode(@RequestParam(value="codeGroup", required=true) String codeGroup,
			@RequestParam(value="code", required=true) String code) {
			
		ResponseEntity<?> result = null;			
												
		commonCodeCommandService.deleteCode(new CommonCodeId(codeGroup, code));
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	
}
