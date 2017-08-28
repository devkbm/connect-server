package com.like.commoncode.domain.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@JsonRootName(value = "commonCodeId")
@Data
@Embeddable
public class CommonCodeId implements Serializable {
		
	private static final long serialVersionUID = -6803140784727174194L;

	private String codeGroup;
	
	@Column(name = "code")
	private String code;
	
	protected CommonCodeId() {}
	
	public CommonCodeId(String codeGroup, String code) {
		this.codeGroup = codeGroup;
		this.code = code;
	}
	
}
