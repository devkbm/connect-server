package com.like.term.domain.repository.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.term.domain.model.QTerm;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class TermQueryDTO implements Serializable {
    	
	private static final long serialVersionUID = -2229885469094955298L;

	String nameKor;
		
	String abbreviation;
	
	String nameEng;
		
	String abbreviationEng;	
	
	private final QTerm qTerm = QTerm.term;
	
	public BooleanBuilder getQuerySpec() {
		BooleanBuilder builder = new BooleanBuilder();
					
		if (StringUtils.hasText(this.nameKor))
			builder.and(qTerm.nameKor.like("%"+nameKor+"%"));
		
		if (StringUtils.hasText(this.abbreviation))
			builder.and(qTerm.abbreviation.like("%"+abbreviation+"%"));
		
		if (StringUtils.hasText(this.nameEng))
			builder.and(qTerm.nameEng.like("%"+nameEng+"%"));
		
		if (StringUtils.hasText(this.abbreviationEng))
			builder.and(qTerm.abbreviationEng.like("%"+abbreviationEng+"%"));
		
		return builder;
	}
}
