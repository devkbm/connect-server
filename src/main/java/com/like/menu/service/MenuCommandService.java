package com.like.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.id.MenuId;
import com.like.menu.infra.jparepository.MenuJpaRepository;

@Service
@Transactional
public class MenuCommandService {

	@Resource(name="menuJpaRepository")
	private MenuJpaRepository menuJpaRepository;
			
	public void saveMenuGroup(MenuGroup codeGroup) {
		menuJpaRepository.saveMenuGroup(codeGroup);	
	}
	
	public void deleteMenuGroup(String menuGroupCode) {
		menuJpaRepository.deleteMenuGroup(menuGroupCode);
	}
	
	public void saveMenu(Menu menu) {
		MenuGroup menuGroup = menuJpaRepository.getMenuGroup(menu.getId().getMenuGroupCode());
		menu.setMenu(menuGroup);
				
		menuJpaRepository.saveMenu(menu);		
	}
	
	public void deleteMenu(MenuId menuId) {
		menuJpaRepository.deleteMenu(menuId);
	}
	
}
