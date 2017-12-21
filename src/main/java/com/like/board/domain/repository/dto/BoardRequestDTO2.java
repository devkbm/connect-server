package com.like.board.domain.repository.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.common.domain.annotation.DTOInfo;

import lombok.Data;

@Data
public class BoardRequestDTO2 implements Serializable {
	
	LocalDateTime sysDt;	
		
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
	
	@DTOInfo(entityName="Board",fieldName="pkBoard")
	Long pkBoard;
	
    /**
     * 상위 게시판 키
     */	
	@DTOInfo(entityName="Board",fieldName="ppkBoard")
    Long ppkBoard;
		
	/**
	 * 게시판_타입
	 */	
	@DTOInfo(entityName="Board",fieldName="boardType")
    String boardType;
	
	/**
     * 게시판 명
     */
    @DTOInfo(entityName="Board",fieldName="boardNm")
	@NotEmpty(message="게시판명은 필수 입력사항입니다.")	
    String boardNm;             
        
    BoardRequestDTO3 group;			
}