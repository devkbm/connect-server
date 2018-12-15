package com.like.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.dto.MenuDTO;
import com.like.menu.dto.MenuGroupDTO;
import com.like.menu.dto.ProgramDTO;
import com.like.menu.infra.jparepository.MenuJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly=true)
public class MenuQueryService {

	@Resource(name="menuJpaRepository")
	private MenuJpaRepository menuJpaRepository;
		
	public MenuGroup getMenuGroup(String menuGroupCode) {
		return menuJpaRepository.getMenuGroup(menuGroupCode);
	}
	
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.QueryCondition condition) {
		return menuJpaRepository.getMenuGroupList(condition);
	}
	
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return menuJpaRepository.getMenuGroupList(likeMenuGroupName);
	}
	
	public List<MenuGroup> getMenuGroupList(List<String> menuGroupCodeList) {
		return menuJpaRepository.getMenuGroupList(menuGroupCodeList);
	}
	
	public Menu getMenu(String menuCode) {
		return menuJpaRepository.getMenu(menuCode);
	}
	
	public List<Menu> getMenuList(String menuGroupCode, MenuDTO.QueryCondition condition) {
		return menuJpaRepository.getMenuList(menuGroupCode, condition);
	}
	
	public List<MenuDTO.MenuHierarchy> getMenuHierachy(String menuGroupCode) {
		List<MenuDTO.MenuHierarchy> rootList = menuJpaRepository.getMenuRootList(menuGroupCode);
		
		return menuJpaRepository.getMenuHierarchyDTO(rootList);
	}
	
	public Program getProgram(String programCode) {
		return menuJpaRepository.getProgram(programCode);
	}
	
	public List<Program> getProgramList(ProgramDTO.QueryCondition condition) {
		return menuJpaRepository.getProgramList(condition);
	}
}
