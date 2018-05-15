package com.like.board.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.enums.PasswordType;
import com.like.common.dto.annotation.DtoField;

import lombok.Data;

@Data
public class BoardSaveDTO implements Serializable {
	
	LocalDateTime sysDt;	
		
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
		
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
    
    String pwdYn;
    
    long sequence;
    
    String pwdMethod;
}