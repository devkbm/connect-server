package com.like.board.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.board.domain.model.QBoard;
import com.like.board.domain.model.enums.BoardType;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Data
public class BoardQueryDTO implements Serializable {

	private final QBoard qBoard = QBoard.board;
	
	String boardName;
	
	String boardType;
		
	
	public BooleanBuilder getBooleanBuilder() {
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(this.boardName)) {
			builder.and(qBoard.boardName.like("%"+this.boardName+"%"));
		}
		
		if (StringUtils.hasText(this.boardType)) {
			builder.and(qBoard.boardType.eq(BoardType.valueOf(boardType)));
		}
		
		return builder;
	}
}
