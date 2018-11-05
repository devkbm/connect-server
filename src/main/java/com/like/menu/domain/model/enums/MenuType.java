package com.like.menu.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MenuType {

	/**
	 * 프로그램 연결이 가능한 메뉴 
	 */
	MENUITEM("A", "메뉴아이템"),
	
	/**
	 * 폴더(프로그램 선택 불가능)
	 */
	FOLDER("B",	"서브메뉴"),
	
	/**
	 * 동일 레벨에서 메뉴를 묶음
	 */
	GROUP("C", "그룹");
				
	private String code;
	private String name;
	
	private MenuType(final String code, final String name) {
		this.code = code;
        this.name = name;               
	}
	
	@JsonValue
    public String getCode() {
        return code;
    }
    	
    public String getName() {
        return name;
    }
}
