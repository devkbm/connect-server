package com.like.board.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.like.board.domain.model.Board;

import lombok.Data;

@Data
public class BoardSaveDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3823369604516101480L;

	LocalDateTime createdDt;	
		
	String createdBy;
		
	LocalDateTime modifiedDt;
		
	String modifiedBy;
		
	Long pkBoard;
	
    /**
     * 상위 게시판 키
     */		
    Long ppkBoard;
		
	/**
	 * 게시판_타입
	 */		
    String boardType;
	
	/**
     * 게시판 명
     */    
	@NotEmpty(message="게시판명은 필수 입력사항입니다.")	
    String boardName;    
        
    String boardDescription;
            
    LocalDate fromDate;
            
    LocalDate toDate;
        
    Boolean useYn;
    
    long articleCount;
            
    long sequence;       
    
    public BoardSaveDTO() {}
    
    public BoardSaveDTO(Board board) {
    	this.pkBoard 	= board.getPkBoard();
    	this.ppkBoard 	= board.getPpkBoard();
    	this.boardType	= board.getBoardType().getCode();
    	this.boardName 	= board.getBoardName();
    	this.boardDescription = board.getBoardDescription();
    	this.fromDate 	= null; // board.getFromDate();
    	this.toDate		= null; // board.getToDate();
    	this.useYn 		= board.getUseYn();
    	this.articleCount = board.getArticleCount();
    	this.sequence	= board.getSequence();    	
    }
        
}
