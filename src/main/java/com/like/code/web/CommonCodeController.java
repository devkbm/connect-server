package com.like.code.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.domain.repository.dto.CommonCodeDTO;
import com.like.code.service.CommonCodeQueryService;
import com.like.code.service.CommonCodeCommandService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;

@RestController
public class CommonCodeController {

	@Resource
	private CommonCodeCommandService commonCodeCommandService;
	
	@Resource
	private CommonCodeQueryService commonCodeQueryService;
	
	private static final Logger log = LoggerFactory.getLogger(CommonCodeController.class);
	
	@RequestMapping(value={"/common/codegroups"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCodeGroups(@RequestParam(value="id", required=false) String codeGroup) {
			
		ResponseEntity<?> result = null;
		
		List<CommonCodeGroup> list = new ArrayList<>(); 		
		
		CommonCodeGroup commonCodeGroup = null;		
		commonCodeGroup = commonCodeQueryService.getCodeGroup(codeGroup); 		
		
		if ( commonCodeGroup != null)
			list.add(commonCodeGroup); 			
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/common/codegroups"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCodeGroup(@RequestBody List<CommonCodeGroup> codeGroupList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		} 
															
		for (CommonCodeGroup codeGroup : codeGroupList ) {
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
		
		if (qType == null) {		
			list = commonCodeQueryService.getCodeList(codeGroup);
		} else {
			list = commonCodeQueryService.getCodeListByComboBox(codeGroup);
		}
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/common/codegroups/codes"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody List<CommonCodeDTO> codeList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		for (CommonCodeDTO code : codeList ) {
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
