package com.like.menu.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;

@Repository
public interface MenuRepository {
	
	MenuGroup getMenuGroup(String menuGroupCode);
	
	List<MenuGroup> getMenuGroupList();
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList);
	
	void saveMenuGroup(MenuGroup menuGroup);
	
	void deleteMenuGroup(String menuGroupCode);
	
	
	Menu getMenu(String menuCode);
	
	List<Menu> getMenuList(String likeMenuName);
			
	void saveMenu(Menu menu);
	
	void deleteMenu(String menuCode);	
}
