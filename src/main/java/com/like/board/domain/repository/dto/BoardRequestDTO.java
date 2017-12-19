package com.like.board.domain.repository.dto;


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
    String boardNm;             
    
    /**
     * 게시판_설명
     */	
    String boardDesc;
    
    /**
     * 시작일자
     */	
	LocalDate fromDt;
    
    /**
     * 종료일자
     */	
    LocalDate toDt;    
    
    /**
     * 사용여부
     */	
    Boolean useYn;
    
    /**
     * 게시글 갯수
     */	
    long articleCnt;
    
    /**
     * 출력순서
     */	
    long seq;
		
	String pwdYn;
		
	String pwdMethod;
			
}