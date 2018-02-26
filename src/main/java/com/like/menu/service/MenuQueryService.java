package com.like.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.domain.repository.dto.MenuHierarchyDTO;
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
	
	public List<MenuGroup> getMenuGroupList() {
		return menuJpaRepository.getMenuGroupList();
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
	
	public List<Menu> getMenuList(String likeMenuName) {
		return menuJpaRepository.getMenuList(likeMenuName);
	}
	
	public List<MenuHierarchyDTO> getMenuHierachy(String menuGroupCode) {
		List<MenuHierarchyDTO> rootList = menuJpaRepository.getMenuChildrenList(menuGroupCode, null);
		log.info(rootList.toString());
		List<MenuHierarchyDTO> list = menuJpaRepository.getMenuHierarchyDTO(rootList);
		
		return list;
	}
	
	public Program getProgram(String programCode) {
		return menuJpaRepository.getProgram(programCode);
	}
}
