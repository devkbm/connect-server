package com.like.menu.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;
import com.like.menu.domain.repository.dto.MenuDTO;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "cmmenu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AuditEntity implements Serializable {
		
	@Id
	@Column(name = "menu_code")
	private String menuCode;
	
	@Column(name="menu_name")
	private String menuName; 		
	
	@Column(name="p_menu_code")
	private String parentMenuCode;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="p_menu_code", insertable=false, updatable=false )
	Menu parent;
	
	@Column(name="seq")
	private long sequence;
	
	@Column(name="level")
	private long level;			
			 				
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_group_code", nullable=false, updatable=false)
	private MenuGroup menuGroup;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_code")
	private Program program;
	
	public Menu() {}
	
	public Menu(String menuCode, String menuName, long sequence, long level) {
		this.menuCode = menuCode;
		this.menuName = menuName;		
		this.sequence = sequence;
		this.level = level;
	}
							
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
	public void registerProgram(Program program) {
		this.program = program;
	}
	
	public static Menu updateEntity(MenuDTO source, Menu target) {
		
		if (target == null) {
			target = new Menu(
						source.getMenuCode(),
						source.getMenuName(), 
						source.getSequence(), 
						source.getLevel());
		} else {
			target.menuCode	= source.getMenuCode();
			target.menuName = source.getMenuName();
			target.sequence = source.getSequence();
			target.level 	= source.getLevel();
			target.parentMenuCode = source.getParentMenuCode();					
		}
		
		return target;
	}

}
