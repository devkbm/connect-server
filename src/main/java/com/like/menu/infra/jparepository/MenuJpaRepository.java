package com.like.menu.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.dto.BoardHierarchyDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.Program;
import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.like.menu.domain.model.QProgram;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.domain.repository.dto.MenuHierarchyDTO;
import com.like.menu.infra.jparepository.springdata.JpaMenu;
import com.like.menu.infra.jparepository.springdata.JpaMenuGroup;
import com.like.menu.infra.jparepository.springdata.JpaProgram;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MenuJpaRepository implements MenuRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaMenuGroup jpaMenuGroup;
	
	@Autowired
	private JpaMenu jpaMenu;
	
	@Autowired
	private JpaProgram jpaProgram;
	
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;	
	private final QProgram qProgram = QProgram.program;

	@Override
	public MenuGroup getMenuGroup(String menuGroupCode) {
		return jpaMenuGroup.findOne(menuGroupCode);
	}

	@Override
	public List<MenuGroup> getMenuGroupList() {
		return jpaMenuGroup.findAll();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(qMenuGroup.menuGroupName.like(likeMenuGroupName+"%"))
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList) {
		return jpaMenuGroup.findAll(menuGroupNameList);
	}

	@Override
	public void saveMenuGroup(MenuGroup codeGroup) {
		jpaMenuGroup.save(codeGroup);		
	}

	@Override
	public void deleteMenuGroup(String menuGroupCode) {
		jpaMenuGroup.delete(menuGroupCode);		
	}

	@Override
	public Menu getMenu(String menuCode) {
		return jpaMenu.findOne(menuCode);
	}

	@Override
	public List<Menu> getMenuList(String likeMenuName) {
		return queryFactory
				.selectFrom(qMenu)
				.where(qMenu.menuName.like(likeMenuName+"%"))
				.fetch();
	}
			
	public List<MenuHierarchyDTO> getMenuChildrenList(String menuGroupCode, String parentMenuCode) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QMenu parent = new QMenu("parent");
		
		Expression<Boolean> isLeaf = new CaseBuilder()
											/*.when(qMenu.menuCode.eq("MENU1")).then(false)
											.when(qMenu.menuCode.eq("MENU2")).then(false)
											.when(qMenu..menuCode.eq("MENU3")).then(false)*/
											.when(qMenu.parentMenuCode.isNull()).then(true)
											.otherwise(false).as("isLeaf");
				
		
		JPAQuery<MenuHierarchyDTO> query = queryFactory
				.select(Projections.constructor(MenuHierarchyDTO.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parentMenuCode, qMenu.sequence, qMenu.level, isLeaf))
				.from(qMenu)								
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode));
										
		if (parentMenuCode == null) {
			builder.and(qMenu.parentMenuCode.isNull());
		} else {
			builder.and(qMenu.parentMenuCode.eq(parentMenuCode));
		}
		query.where(builder);
				
		return query.fetch();
	}
	

	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	public List<MenuHierarchyDTO> getMenuHierarchyDTO(List<MenuHierarchyDTO> list) {
		List<MenuHierarchyDTO> children = null;
		
		for ( MenuHierarchyDTO dto : list ) {			
			if (dto.isLeaf()) { // leaf 노드이면 다음 리스트 검색
				continue;
			} else {
				log.info(dto.getMenuGroupCode() + " : "+ dto.getMenuCode());
				children = getMenuChildrenList(dto.getMenuGroupCode(), dto.getMenuCode());
				dto.setChildren(children);
				
				getMenuHierarchyDTO(children);
			}
		}
		
		return list;
	}

	@Override
	public void saveMenu(Menu menu, MenuGroup menuGroup) {
		menu.setMenuGroup(menuGroup);		
		jpaMenu.save(menu);		
	}

	@Override
	public void deleteMenu(String menuCode) {
		jpaMenu.delete(menuCode);
	}

	@Override
	public Program getProgram(String programCode) {		
		return jpaProgram.findOne(programCode);
	}

	@Override
	public List<Program> getProgramList() {
		return jpaProgram.findAll();
	}

	@Override
	public void saveProgram(Program program, Menu menu) {
		menu.setProgram(program);
		jpaProgram.save(program);		
	}

	@Override
	public void deleteProgram(String programCode) {
		jpaProgram.delete(programCode);		
	}					
	
}