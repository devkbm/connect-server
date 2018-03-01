package com.like.menu;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.domain.model.Board;
import com.like.file.service.FileService;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.service.MenuCommandService;
import com.like.menu.service.MenuQueryService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceTest {

	private static final Logger log = LoggerFactory.getLogger(MenuServiceTest.class);
		 
	@Autowired
	MenuCommandService cs;
	
	@Autowired
	MenuQueryService qs;	
	
	@Autowired
	FileService fs;
		
	@Before 
    public void setUp() throws Exception { 
		//테스트 데이터 입력
		MenuGroup menuGroup = new MenuGroup("GROUP","테스트메뉴그룹","테스트메뉴그룹");				
		cs.saveMenuGroup(menuGroup);
		
		Menu menu = new Menu("MENU","테스트메뉴", 0, 0);		
		cs.saveMenu(menu, menuGroup.getMenuGroupCode());
					
		Program program = new Program("Program","테스트프로그램","/home","테스트프로그램입니다.");		
		cs.saveProgram(program);		
    } 
	
	@Test	
	public void test01_메뉴그룹등록() {
		
		MenuGroup menuGroup = new MenuGroup("Test","테스트메뉴그룹","테스트메뉴그룹");		
		
		cs.saveMenuGroup(menuGroup);
		
		assertThat(menuGroup.getMenuGroupCode(), is("Test"));
		assertThat(menuGroup.getMenuGroupName(), is("테스트메뉴그룹"));
		assertThat(menuGroup.getDescription(), is("테스트메뉴그룹"));
	}
	
	@Test	
	public void test02_메뉴그룹조회() {			
		
		MenuGroup menuGroup = qs.getMenuGroup("GROUP");					
		
		assertThat(menuGroup.getMenuGroupCode(), is("GROUP"));
		assertThat(menuGroup.getMenuGroupName(), is("테스트메뉴그룹"));
		assertThat(menuGroup.getDescription(), is("테스트메뉴그룹"));		
	}
	
	@Test
	public void test03_메뉴등록() throws Exception {
		Menu menu = new Menu("testmenu","테스트메뉴", 0, 0);
		
		cs.saveMenu(menu, "GROUP");		
		
		assertThat(menu.getMenuCode(), is("testmenu"));
		assertThat(menu.getMenuName(), is("테스트메뉴"));
		assertThat(menu.getSequence(), is(0L));
		assertThat(menu.getLevel(), is(0L));		
	}
	
	@Test
	public void test04_프로그램등록() throws Exception {	
		Program program = new Program("Program","테스트프로그램","/home","테스트프로그램입니다.");
		
		cs.saveProgram(program);				
	}
	
}
