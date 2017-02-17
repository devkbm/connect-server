package com.like.common.file.infra.mapper.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class FileListDTO implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5209972853064339607L;

	/**
	 * 생성일자
	 */
	LocalDate sysDt;	
	
	/**
	 * 생성유저		
	 */
	String sysUser;
		
	/**
	 * 수정일자			
	 */
	LocalDate updDt;
	
	/**
	 * 수정유저			
	 */
	String updUser;	
	
	/**
	 * 키
	 */
	String id;	
	
	/**
	 * 파일명 
	 */
	String name;		
	
	/**
	 * 파일 사이즈 
	 */
	Long size;
	
	/*
	 * 상태 
	 */	
	String status;	
    
	/**
	 * 외래키
	 */
	String fk;
            
}
