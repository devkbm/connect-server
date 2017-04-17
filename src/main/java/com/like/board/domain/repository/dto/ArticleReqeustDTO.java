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
    
    String pkArticle;	
	
    String ppkArticle;		
	
	String title;
    
    String contents;
     
    String pwd;
    
    String hitCnt;
        
    String fromDt;
    
    String toDt;
    
    Integer seq;
    
    Integer depth;
    	
    Long fkBoard;
    
    @JsonIgnore
    MultipartFile file;
}
