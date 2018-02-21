package com.like.menu.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.common.domain.AuditEntity;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.menu.domain.model.id.MenuId;

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
			
	@Column(name="url")
	private String url;
			 				
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_group_code", nullable=false, updatable=false)
	private MenuGroup menuGroup;
	
	public Menu() {}
	
	public Menu(String menuCode, String menuName, MenuGroup menuGroup) {
		this.menuCode = menuCode;
		this.menuName = menuName;
	}
				
	public Menu setMenuInfo(String menuName, String parentMenuCode, long sequence, long level, String url) {		
		this.menuName = menuName;
		this.parentMenuCode = parentMenuCode;
		this.sequence = sequence;
		this.level = level;
		this.url = url;
		
		return this;
	}
	
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
}
