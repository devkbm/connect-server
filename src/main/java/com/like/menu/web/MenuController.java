package com.like.menu.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

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

import com.like.common.domain.DTOConverter;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.id.MenuId;
import com.like.menu.domain.repository.dto.MenuDTO;
import com.like.menu.domain.repository.dto.MenuGroupDTO;
import com.like.menu.domain.repository.dto.MenuHierarchyDTO;
import com.like.menu.service.MenuCommandService;
import com.like.menu.service.MenuQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuController {

	@Resource
	private MenuCommandService menuCommandService;
	
	@Resource
	private MenuQueryService menuQueryService;
			
	@GetMapping("/menugroup/{id}")
	public ResponseEntity<?> getMenuGroup(@PathVariable(value="id") String menuGroupCode) {
			
		ResponseEntity<?> result = null;
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode); 		
				
		result = WebControllerUtil.getResponse(menuGroup, 
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@GetMapping("/menutest/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHier(@PathVariable(value="menuGroupCode") String menuGroupCode) {
			
		ResponseEntity<?> result = null;
		
		List<MenuHierarchyDTO> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 		
				
		result = WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@GetMapping("/menugroup")
	public ResponseEntity<?> getMenuGroupList() {
			
		ResponseEntity<?> result = null;
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(); 		
				
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/menugroup/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO menuGroupDTO, BindingResult result) throws IllegalArgumentException, IllegalAccessException, SecurityException, InstantiationException {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupDTO.getMenuGroupCode());
															
		menuGroup = DTOConverter.convertEntityByAnnotation(menuGroupDTO, menuGroup, MenuGroup.class);
			
		menuCommandService.saveMenuGroup(menuGroup);
		
		res = WebControllerUtil.getResponse(null,
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
										 					
		return res;
	}
		
	@DeleteMapping("/menugroup/{id}")
	public ResponseEntity<?> delCodeGroup(@PathVariable(value="id") String menuGroupCode) {
			
		ResponseEntity<?> result = null;			
												
		menuCommandService.deleteMenuGroup(menuGroupCode);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	
	@GetMapping("/menugroup/{groupcode}/menu/{menucode}")
	public ResponseEntity<?> getMenu(
			@PathVariable(value="groupcode") String menuGroupCode,
			@PathVariable(value="menucode") String menuCode) {
			
		ResponseEntity<?> result = null;
		
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		MenuDTO dto = new MenuDTO(menu);
		
		result = WebControllerUtil.getResponse(dto, 
				dto != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@GetMapping("/menugroup/{groupcode}/menu")
	public ResponseEntity<?> getMenuList(
			@PathVariable(value="groupcode") String menuGroupCode) {
			
		ResponseEntity<?> result = null;
		
		List<Menu> list = menuQueryService.getMenuList(menuGroupCode); 		
				
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	
	@RequestMapping(value={"/menugroup/{groupcode}/menu/{menucode}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO menuDTO, BindingResult result) throws IllegalArgumentException, IllegalAccessException, SecurityException, InstantiationException, InvocationTargetException {
			
		ResponseEntity<?> res = null;
						
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Menu menu = menuQueryService.getMenu(menuDTO.getMenuCode());			
				
		menu = DTOConverter.convertEntityByAnnotation(menuDTO, menu, Menu.class);		
					
		menuCommandService.saveMenu(menu, menuDTO.getMenuGroupCode());																			
		
		res = WebControllerUtil.getResponse(null,
				menu != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menu != null ? 1 : 0), 
				HttpStatus.OK);
										 					
		return res;
	}
	
	
}
