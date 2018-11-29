package com.like.menu.web;

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
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.dto.EnumDTO;
import com.like.menu.dto.MenuDTO;
import com.like.menu.dto.MenuGroupDTO;
import com.like.menu.dto.MenuGroupQueryDTO;
import com.like.menu.dto.MenuHierarchyDTO;
import com.like.menu.dto.MenuQueryDTO;
import com.like.menu.dto.ProgramQueryDTO;
import com.like.menu.dto.ProgramSaveDTO;
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
	public ResponseEntity<?> getMenuGroupHierachyTest(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuHierarchyDTO> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuHierarchyDTO> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menugroup")
	public ResponseEntity<?> getMenuGroupList(MenuGroupQueryDTO dto) {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(dto); 							
		
		return WebControllerUtil.getResponse(list,
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/menugroup/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO menuGroupDTO, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupDTO.getMenuGroupCode());
		
		if (menuGroup == null) {
			menuGroup = new MenuGroup(menuGroupDTO.getMenuGroupCode(), menuGroupDTO.getMenuGroupName(), menuGroupDTO.getDescription());
		} else {
			menuGroup = menuGroup.updateEntity(menuGroupDTO);
		}					
																			
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
			@PathVariable(value="groupcode") String menuGroupCode,
			MenuQueryDTO dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(menuGroupCode, dto);														 						
		
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<EnumDTO>();
		
		for (MenuType menuType : MenuType.values()) {
			EnumDTO dto = new EnumDTO(menuType.toString(), menuType.getName());
			list.add(dto);
		}				 					
		
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/menu/{menucode}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors()) {
			//throw new ControllerException("오류");
			log.info(result.getAllErrors().toString());
		} 
		
		Menu menu = menuQueryService.getMenu(dto.getMenuCode());			
						
		if ( menu == null ) {					
			
			MenuGroup menuGroup = menuQueryService.getMenuGroup(dto.getMenuGroupCode());
			Program program = menuQueryService.getProgram(dto.getProgram());
			
			menu = new Menu(dto.getMenuCode(), 
							dto.getMenuName(),
							dto.getParentMenuCode(),
							MenuType.valueOf(dto.getMenuType()), 
							dto.getSequence(), 
							dto.getLevel(),
							menuGroup,
							program);
		} else {
			menu.updateEntity(dto);
		}
		
		if (dto.getProgram() != null) {
			Program program = menuQueryService.getProgram(dto.getProgram());
			menu.registerProgram(program);
		}
					
		menuCommandService.saveMenu(menu, dto.getMenuGroupCode());																			
														 				
		return WebControllerUtil.getResponse(null,
				menu != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menu != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<?> delMenu(@PathVariable(value="id") String menuCode) {				
												
		menuCommandService.deleteMenu(menuCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/program")
	public ResponseEntity<?> getProgramList(ProgramQueryDTO condition) {							 			
		
		List<Program> list = menuQueryService.getProgramList(condition);
										
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
	public ResponseEntity<?> saveProgram(@RequestBody @Valid ProgramSaveDTO dto, BindingResult result) throws Exception {
										
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		Program program = menuQueryService.getProgram(dto.getProgramCode());							
		
		if ( program == null ) {
			program = new Program(dto.getProgramCode(), dto.getProgramName(), dto.getUrl(), dto.getDescription());					
		} else {
			program.updateEntity(dto);
		}
					
		menuCommandService.saveProgram(program);																						
										 					
		return WebControllerUtil.getResponse(null,
				program != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", program != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@DeleteMapping("/program/{code}")
	public ResponseEntity<?> delProgram(@PathVariable(value="code") String programCode) {				
												
		menuCommandService.deleteProgram(programCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
}
