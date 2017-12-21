package com.like.board.domain.repository.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


import org.hibernate.validator.constraints.NotEmpty;

import com.like.common.domain.annotation.DTOInfo;

import lombok.Data;

@Data
public class BoardRequestDTO3 implements Serializable {
				                
    /**
     * 게시판_설명
     */	
    @DTOInfo(entityName="Board",fieldName="boardDesc")
    String boardDesc;
    
    /**
     * 시작일자
     */	
    @DTOInfo(entityName="Board",fieldName="fromDt")
	LocalDate fromDt;
    
    /**
     * 종료일자
     */	
    @DTOInfo(entityName="Board",fieldName="toDt")
    LocalDate toDt;    
    
    /**
     * 사용여부
     */	
    @DTOInfo(entityName="Board",fieldName="useYn")
    Boolean useYn;
        	
}