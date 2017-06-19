package com.like.code.domain.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CommonCodeId implements Serializable {
	
	private String codeGroup;
	
	@Column(name = "code")
	private String code;
}
