package com.like.board.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.Article;
import com.like.common.domain.annotation.DtoField;

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
    
	@DtoField(targetEntity=Article.class)
    Long pkArticle;	
	
	@DtoField(targetEntity=Article.class)
	Long ppkArticle;		
	
	@DtoField(targetEntity=Article.class)
	String title;
    
	@DtoField(targetEntity=Article.class)
    String contents;
    
	@DtoField(targetEntity=Article.class)
    String pwd;
    
	@DtoField(targetEntity=Article.class)
    String hitCount;
        
	@DtoField(targetEntity=Article.class)
    String fromDt;
    
	@DtoField(targetEntity=Article.class)
    String toDt;
    
	@DtoField(targetEntity=Article.class)
    Integer seq;
    
	@DtoField(targetEntity=Article.class)
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    MultipartFile file;
        
}
