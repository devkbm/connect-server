package com.like.menu.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;
import com.like.menu.web.dto.ProgramSaveDTO;

import lombok.Getter;

@Getter
@Entity
@Table(name = "comprogram")
@EntityListeners(AuditingEntityListener.class)
public class Program extends AuditEntity implements Serializable{

	@Id
	@Column(name="program_code")
	private String programCode;
	
	@Column(name="program_name")
	private String programName; 
		
	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="program", fetch=FetchType.LAZY)          
    List<Menu> menuList = new ArrayList<Menu>();
	
	public Program() {}

	public Program(String programCode, String programName, String url, String description) {		
		this.programCode = programCode;
		this.programName = programName;
		this.url = url;
		this.description = description;
	}
	
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void registerMenu(Menu menu) {
		this.menuList.add(menu);
	}
	
	public Program updateEntity(ProgramSaveDTO dto) {
		this.programCode 	= dto.getProgramCode() != null ? dto.getProgramCode() : this.programCode ;
		this.programName 	= dto.getProgramName() != null ? dto.getProgramName() : this.programName ;
		this.url			= dto.getUrl() != null ? dto.getUrl() : this.url ;
		this.description	= dto.getDescription() != null ? dto.getDescription() : this.description ;
		
		return this;
	}
			
}
