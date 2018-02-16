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
    
	@DTOInfo(targetEntity=Article.class)
    Long pkArticle;	
	
	@DTOInfo(targetEntity=Article.class)
	Long ppkArticle;		
	
	@DTOInfo(targetEntity=Article.class)
	String title;
    
	@DTOInfo(targetEntity=Article.class)
    String contents;
    
	@DTOInfo(targetEntity=Article.class)
    String pwd;
    
	@DTOInfo(targetEntity=Article.class)
    String hitCount;
        
	@DTOInfo(targetEntity=Article.class)
    String fromDt;
    
	@DTOInfo(targetEntity=Article.class)
    String toDt;
    
	@DTOInfo(targetEntity=Article.class)
    Integer seq;
    
	@DTOInfo(targetEntity=Article.class)
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    MultipartFile file;
        
}
