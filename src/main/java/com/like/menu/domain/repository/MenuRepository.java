package com.like.menu.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.id.MenuId;

@Repository
public interface MenuRepository {
	
	MenuGroup getMenuGroup(String menuGroupCode);
	
	List<MenuGroup> getMenuGroupList();
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList);
	
	void saveMenuGroup(MenuGroup codeGroup);
	
	void deleteMenuGroup(String menuGroupCode);
	
	
	Menu getMenu(MenuId menuId);
	
	List<Menu> getMenuList(String likeMenuName);
			
	void saveMenu(Menu menu);
	
	void deleteMenu(MenuId menuId);	
}
