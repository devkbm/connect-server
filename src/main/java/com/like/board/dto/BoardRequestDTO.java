package com.like.board.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class BoardRequestDTO implements Serializable {
	
	LocalDateTime sysDt;	
		
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
	
	@Id
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
    
    /**
     * 게시판_설명
     */	
    String boardDescription;
    
    /**
     * 시작일자
     */	
	LocalDate fromDate;
    
    /**
     * 종료일자
     */	
    LocalDate toDate;    
    
    /**
     * 사용여부
     */	
    Boolean useYn;
    
    /**
     * 게시글 갯수
     */	
    long articleCount;
    
    /**
     * 출력순서
     */	
    long sequence;
		
	String pwdYn;
		
	String pwdMethod;
			
}