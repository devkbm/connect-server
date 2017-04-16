package com.like.board.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ArticleReqeustDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844786437530688768L;

	/*Long pkArticle;	
		
	Long ppkArticle;		
	
	String title;
    
    String contents;
     
    String pwd;
    
    int hitCnt;
        
    LocalDate fromDt;
    
    LocalDate toDt;
    
    Long seq;
    
    long depth;
    	
    Long fkBoard;
    
    @JsonIgnore
    MultipartFile file;*/
    
    String pkArticle;	
	
    String ppkArticle;		
	
	String title;
    
    String contents;
     
    String pwd;
    
    String hitCnt;
        
    String fromDt;
    
    String toDt;
    
    String seq;
    
    String depth;
    	
    Long fkBoard;
    
    @JsonIgnore
    MultipartFile file;
}
