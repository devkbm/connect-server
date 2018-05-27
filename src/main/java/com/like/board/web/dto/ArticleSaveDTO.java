package com.like.board.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.board.domain.model.Article;

import lombok.Data;

@Data
public class ArticleSaveDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844786437530688768L;
	
	LocalDateTime sysDt;	
	
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
    	
    Long pkArticle;	
	
	Long ppkArticle;		
		
	String title;
    	
    String contents;
    	
    String pwd;
    	
    int hitCount;
        	
    LocalDate fromDate;
    	
    LocalDate toDate;
    	
    Integer seq;
    	
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    MultipartFile file;
    
    @JsonProperty("file")
    String fileName;
    
    public ArticleSaveDTO(){}
    
    public ArticleSaveDTO(Article article) {
    	this.pkArticle 	= article.getPkArticle();
    	this.ppkArticle = article.getPpkArticle();
    	this.title 		= article.getTitle();
    	this.contents	= article.getContents();
    	this.pwd		= article.getPwd();
    	this.hitCount	= article.getHitCount();
    	this.fromDate 	= article.getFromDate();
    	this.toDate		= article.getToDate();
    	this.seq 		= article.getSeq();
    	this.depth		= article.getDepth();
    	this.fkBoard	= article.getBoard().getPkBoard();
    	this.fileName   = article.getFiles().get(0).getFileNm();
    }
        
}
