package com.like.board.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.like.board.domain.model.enums.BoardType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardHierarchyDTO implements Serializable {

	private static final long serialVersionUID = -4534626900084055780L;

	Long pkBoard;
	
	Long ppkBoard;
	
	BoardType boardType;
	
	String boardName;
	
	String boardDescription;
	
	LocalDate fromDate;
	
	LocalDate toDate;
	
	Long articleCount;
	
	Long sequence;
			
	private boolean expanded;
	
	private boolean selected;
	
	private boolean isLeaf;
	
	private boolean active;
	
	private List<BoardHierarchyDTO> children;
			
	@QueryProjection
	public BoardHierarchyDTO(
			Long pkBoard, Long ppkBoard, BoardType boardType, 
			String boardName, String boardDescription, LocalDate fromDate, 
			LocalDate toDate, Long articleCount, Long sequence, Boolean isLeaf) {
		super();
		this.pkBoard = pkBoard;
		this.ppkBoard = ppkBoard;
		this.boardType = boardType;
		this.boardName = boardName;
		this.boardDescription = boardDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.articleCount = articleCount;
		this.sequence = sequence;
		this.expanded = false;
		this.selected = false;
		this.active = false;
	}
	
	
	
}
