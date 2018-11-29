package com.like.menu.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.dto.MenuGroupQueryDTO;
import com.like.menu.dto.MenuQueryDTO;
import com.like.menu.dto.ProgramQueryDTO;

@Repository
public interface MenuRepository {
	
	MenuGroup getMenuGroup(String menuGroupCode);
	
	List<MenuGroup> getMenuGroupList(MenuGroupQueryDTO condition);
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList);
	
	void saveMenuGroup(MenuGroup menuGroup);
	
	void deleteMenuGroup(String menuGroupCode);
	
	
	Menu getMenu(String menuCode);
			
	List<Menu> getMenuList(String menuGroupCode, MenuQueryDTO condition);
			
	void saveMenu(Menu menu, MenuGroup menuGroup);
	
	void deleteMenu(String menuCode);	
	
	
	Program getProgram(String programCode);
	
	List<Program> getProgramList(ProgramQueryDTO condition);
	
	void saveProgram(Program program);
	
	void deleteProgram(String programCode);
	
}
