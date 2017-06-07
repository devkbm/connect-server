package com.like.board.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PasswordType {
	SHA224("SHA224","SHA-224"),
	SHA256("SHA256","SHA-256"),
	SHA384("SHA384","SHA-384"),
	SHA512("SHA512","SHA-512");
	
	private String code;
	private String name;
	
	private PasswordType(final String code, final String name) {
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
