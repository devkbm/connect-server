package com.like.menu.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.domain.repository.dto.MenuDTO;
import com.like.menu.domain.repository.dto.MenuGroupDTO;
import com.like.menu.domain.repository.dto.MenuHierarchyDTO;
import com.like.menu.domain.repository.dto.ProgramSaveDTO;
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
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode); 		
								
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menutest/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuHierarchyDTO> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menugroup")
	public ResponseEntity<?> getMenuGroupList() {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(); 							
		
		return WebControllerUtil.getResponse(list,
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/menugroup/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO menuGroupDTO, BindingResult result) {				
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupDTO.getMenuGroupCode());
		
		menuGroup = MenuGroup.updateEntity(menuGroupDTO, menuGroup);
																			
		menuCommandService.saveMenuGroup(menuGroup);			
										 					
		return WebControllerUtil.getResponse(null,
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
	}
		
	@DeleteMapping("/menugroup/{id}")
	public ResponseEntity<?> delCodeGroup(@PathVariable(value="id") String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(menuGroupCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/menugroup/{groupcode}/menu/{menucode}")
	public ResponseEntity<?> getMenu(
			@PathVariable(value="groupcode") String menuGroupCode,
			@PathVariable(value="menucode") String menuCode) {				
		
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		MenuDTO dto = new MenuDTO(menu);			
		
		return WebControllerUtil.getResponse(dto, 
				dto != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menugroup/{groupcode}/menu")
	public ResponseEntity<?> getMenuList(
			@PathVariable(value="groupcode") String menuGroupCode) {				
		
		List<Menu> list = menuQueryService.getMenuList(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/menugroup/{groupcode}/menu/{menucode}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO menuDTO, BindingResult result) throws Exception {
											
		if ( result.hasErrors()) {
			//throw new ControllerException("오류");
			log.info(result.getAllErrors().toString());
		} 
		
		Menu menu = menuQueryService.getMenu(menuDTO.getMenuCode());			
		
		menu = Menu.updateEntity(menuDTO, menu);
		
		if (menuDTO.getProgram() != null) {
			menu.registerProgram(menuQueryService.getProgram(menuDTO.getProgram()));
		}
					
		menuCommandService.saveMenu(menu, menuDTO.getMenuGroupCode());																			
														 				
		return WebControllerUtil.getResponse(null,
				menu != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menu != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/program")
	public ResponseEntity<?> getProgramList() {
							 		
		List<Program> list = menuQueryService.getProgramList();
										
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 
	}
	
	@GetMapping("/program/{code}")
	public ResponseEntity<?> getProgram(
			@PathVariable(value="code") String programCode) {				
		
		Program program = menuQueryService.getProgram(programCode); 							
		
		return WebControllerUtil.getResponse(program, 
				program != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", program != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/program/{code}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveProgram(@RequestBody @Valid ProgramSaveDTO programSaveDTO, BindingResult result) throws Exception {
										
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Program program = menuQueryService.getProgram(programSaveDTO.getProgramCode());			
				
		program = Program.updateEntity(programSaveDTO, program);		
					
		menuCommandService.saveProgram(program);																						
										 					
		return WebControllerUtil.getResponse(null,
				program != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", program != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	
}
