package com.like.board.domain.model;

import com.like.board.domain.model.enums.BoardType;
import com.like.board.dto.BoardDTO;
import com.like.board.dto.BoardDTO.BoardSaveDTO;


public class BoardDTOAssembler {	
		
	public static Board createEntity(BoardDTO.BoardSaveDTO dto) {
		
		return Board.builder()
					.boardName(dto.getBoardName())
					.boardType(BoardType.valueOf(dto.getBoardType()))
					.boardDescription(dto.getBoardDescription())
					.fromDate(dto.getFromDate())
					.toDate(dto.getToDate())
					.useYn(dto.getUseYn())
					.sequence(dto.getSequence())
					.build();
	}	
	
	public static Board mergeEntity(Board entity, BoardDTO.BoardSaveDTO dto) {
		
		entity.boardName 		= nvl(dto.getBoardName(), entity.boardName);
		entity.boardType 		= nvl(BoardType.valueOf(dto.getBoardType()), entity.boardType);
		entity.boardDescription	= nvl(dto.getBoardDescription(), entity.boardDescription);
		entity.fromDate			= nvl(dto.getFromDate(), entity.fromDate);
		entity.toDate			= nvl(dto.getToDate(), entity.toDate);
		entity.sequence			= nvl(dto.getSequence(), entity.sequence);
		entity.useYn			= nvl(dto.getUseYn(), entity.useYn);
		
		return entity;
	}
	
	
	public static BoardDTO.BoardSaveDTO convertDTO(Board entity) {					
				
		if (entity == null)
			return null;
		
		BoardSaveDTO dto = BoardSaveDTO.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.pkBoard(entity.pkBoard)
								.ppkBoard(entity.ppkBoard)
								.boardType(entity.boardType.toString())
								.boardName(entity.boardName)
								.boardDescription(entity.boardDescription)
								.fromDate(entity.fromDate)
								.toDate(entity.toDate)
								.useYn(entity.useYn)
								.articleCount(entity.articleCount)
								.sequence(entity.sequence)
								.build();		
		return dto;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
