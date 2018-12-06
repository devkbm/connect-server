package com.like.board.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.board.domain.model.Article;
import com.like.file.domain.model.FileInfo;

import lombok.Data;

@Data
public class ArticleSaveDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844786437530688768L;
	
	LocalDateTime createdDt;	
	
	String createdBy;
		
	LocalDateTime modifiedDt;
		
	String modifiedBy;
    	
    Long pkArticle;	
	
	Long ppkArticle;		
		
	String title;
    	
    String contents;
    	
    String pwd;
    	
    int hitCount;
        
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fromDate;
    	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate toDate;
    	
    Integer seq;
    	
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    List<MultipartFile> file;
                
    List<AttachFile> attachFile = new ArrayList<AttachFile>();
    
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
    	    	
		for (FileInfo info : article.getFiles()) {
			attachFile.add(new AttachFile(info.getPkFile(), info.getFileNm()));
		}    		
    	
    }
        
}
