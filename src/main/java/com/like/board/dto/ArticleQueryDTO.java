package com.like.board.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.board.domain.model.QArticle;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class ArticleQueryDTO implements Serializable {

	private final QArticle qArticle = QArticle.article;
	
	Long fkBoard;
	
	String title;
	
	String contents;
		
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qArticle.board.pkBoard.eq(fkBoard));
		
		if (StringUtils.hasText(this.title)) {
			builder.and(qArticle.title.like("%"+this.title+"%"));
		}
		
		if (StringUtils.hasText(this.contents)) {
			builder.and(qArticle.contents.like("%"+this.contents+"%"));
		}
		
		return builder;
	}
}
