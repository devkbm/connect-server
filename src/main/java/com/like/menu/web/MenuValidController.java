package com.like.menu.web;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.dto.MenuDTO;
import com.like.menu.service.MenuQueryService;

@RestController
public class MenuValidController {

	@Resource
	private MenuQueryService menuQueryService;
	
	@GetMapping("/common/menugroup/{id}/check")
	public ResponseEntity<?> getMenuGroupValid(@PathVariable(value="id") String menuGroupCode) {				
		Boolean isValid = false;
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode);
		
		if (menuGroup == null) {
			isValid = true;
		} else { 
			isValid = false;
		}
								
		return WebControllerUtil.getResponse(isValid, 
				1, 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menugroup/{groupcode}/menu/{menucode}")
	public ResponseEntity<?> getMenu(
			@PathVariable(value="groupcode") String menuGroupCode,
			@PathVariable(value="menucode") String menuCode) {				
		
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		MenuDTO.MenuSave dto = new MenuDTO.MenuSave(menu);			
		
		return WebControllerUtil.getResponse(dto, 
				dto != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0), 
				HttpStatus.OK);
	}
}
