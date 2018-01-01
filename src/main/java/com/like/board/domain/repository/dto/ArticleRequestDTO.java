package com.like.board.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.Article;
import com.like.common.domain.annotation.DTOInfo;

import lombok.Data;

@Data
public class ArticleRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844786437530688768L;
	
	LocalDateTime sysDt;	
	
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
    
	@DTOInfo(classInstance=Article.class)
    Long pkArticle;	
	
	@DTOInfo(classInstance=Article.class)
	Long ppkArticle;		
	
	@DTOInfo(classInstance=Article.class)
	String title;
    
	@DTOInfo(classInstance=Article.class)
    String contents;
    
	@DTOInfo(classInstance=Article.class)
    String pwd;
    
	@DTOInfo(classInstance=Article.class)
    String hitCount;
        
	@DTOInfo(classInstance=Article.class)
    String fromDt;
    
	@DTOInfo(classInstance=Article.class)
    String toDt;
    
	@DTOInfo(classInstance=Article.class)
    Integer seq;
    
	@DTOInfo(classInstance=Article.class)
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    MultipartFile file;
    
}
