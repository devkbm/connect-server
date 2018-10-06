package com.like.board.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.enums.PasswordType;

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
}