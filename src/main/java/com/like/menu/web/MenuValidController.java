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
	
	@GetMapping("/menu/{menucode}/check")
	public ResponseEntity<?> getMenu(			
			@PathVariable(value="menucode") String menuCode) {				
		Boolean isValid = false;
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		if (menu == null) {
			isValid = true;
		} else { 
			isValid = false;
		}				
		
		return WebControllerUtil.getResponse(isValid, 
				1, 
				true, 
				String.format("%d 건 조회되었습니다.", menu != null ? 1 : 0), 
				HttpStatus.OK);
	}
}
