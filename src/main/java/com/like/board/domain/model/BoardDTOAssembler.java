package com.like.board.domain.model;

import javax.annotation.Resource;

import com.like.board.domain.model.enums.BoardType;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.dto.ArticleDTO;
import com.like.board.dto.BoardDTO;
import com.like.board.dto.BoardDTO.BoardSaveDTO;


public class BoardDTOAssembler {	
	
	@Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
		
	public static Board createEntity(BoardDTO.BoardSaveDTO dto, Board parentBoard) {
		
		return Board.builder()
					.parent(parentBoard)
					.boardName(dto.getBoardName())
					.boardType(BoardType.valueOf(dto.getBoardType()))
					.boardDescription(dto.getBoardDescription())
					.fromDate(dto.getFromDate())
					.toDate(dto.getToDate())
					.useYn(dto.getUseYn())
					.sequence(dto.getSequence())
					.build();
	}	
	
	public static Board mergeEntity(Board entity, BoardDTO.BoardSaveDTO dto, Board parentBoard) {
						
		entity.parent			= parentBoard;
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
								.ppkBoard(entity.getParent() == null ? null : entity.getParent().getPkBoard())
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
	
	public static Article createEntity(ArticleDTO.ArticleSave dto, Board board) {
						
		return Article.builder()	
						.board(board)
						.ppkArticle(dto.getPpkArticle())
						.title(dto.getTitle())
						.contents(dto.getContents())
						.fromDate(dto.getFromDate())
						.toDate(dto.getToDate())
						.pwd(dto.getPwd())
						.build();
	}
	
	public static Article mergeEntity(Article entity, ArticleDTO.ArticleSave dto) {
					
		entity.ppkArticle 		= nvl(dto.getPpkArticle(), entity.ppkArticle);
		entity.title 			= nvl(dto.getTitle(), entity.title);
		entity.contents			= nvl(dto.getContents(), entity.contents);
		entity.pwd				= nvl(dto.getPwd(), entity.pwd);
		entity.fromDate			= nvl(dto.getFromDate(), entity.fromDate);
		entity.toDate			= nvl(dto.getToDate(), entity.toDate);
		entity.seq				= nvl(dto.getSeq(), entity.seq);
		
		return entity;
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
