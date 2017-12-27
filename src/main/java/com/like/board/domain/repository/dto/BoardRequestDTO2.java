package com.like.board.domain.repository.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.board.domain.model.Board;
import com.like.board.domain.model.enums.PasswordType;
import com.like.common.domain.annotation.DTOInfo;

import lombok.Data;

@Data
public class BoardRequestDTO2 implements Serializable {
	
	LocalDateTime sysDt;	
		
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
	
	@DTOInfo(classInstance=Board.class,fieldName="pkBoard")
	Long pkBoard;
	
    /**
     * 상위 게시판 키
     */	
	@DTOInfo(classInstance=Board.class,fieldName="ppkBoard")
    Long ppkBoard;
		
	/**
	 * 게시판_타입
	 */	
	@DTOInfo(classInstance=Board.class,fieldName="boardType")
    String boardType;
	
	/**
     * 게시판 명
     */
    @DTOInfo(classInstance=Board.class,fieldName="boardName")
	@NotEmpty(message="게시판명은 필수 입력사항입니다.")	
    String boardName;    
    
    String boardDescription;
    
    LocalDate fromDate;
    
    LocalDate toDate;
    
    Boolean useYn;
    
    long articleCount;
    
    String pwdYn;
    
    long sequence;
    
    PasswordType pwdMethod;
}