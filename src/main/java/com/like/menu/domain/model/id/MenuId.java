package com.like.menu.domain.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@JsonRootName(value = "munuId")
@Data
@Embeddable
public class MenuId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10644605798162751L;

	private String menuGroupCode;
	
	@Column(name = "menu_code")
	private String menuCode;
	
	protected MenuId() {}
	
	public MenuId(String menuGroupCode, String menuCode) {
		this.menuGroupCode = menuGroupCode;
		this.menuCode = menuCode;		
	}
}
