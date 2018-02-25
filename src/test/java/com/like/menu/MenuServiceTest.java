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
	
	Long key;
	
	@Before 
    public void setUp() { 

    } 
	
	@Test	
	public void test01_메뉴그룹등록() {
		
		MenuGroup menuGroup = new MenuGroup("Test","테스트메뉴그룹","테스트메뉴그룹");		
		
		cs.saveMenuGroup(menuGroup);									
	}
	
	@Test	
	public void test02_메뉴그룹조회() {
		
		MenuGroup menuGroup = qs.getMenuGroup("Test");		
		
		assertThat(menuGroup.getMenuGroupName(), is("테스트메뉴그룹"));				
	}
	
	@Test
	public void test03_메뉴등록() {
		Menu menu = new Menu("testmenu","테스트메뉴",0,0);
		
		cs.saveMenu(menu, "Test");		
	}
	
}
