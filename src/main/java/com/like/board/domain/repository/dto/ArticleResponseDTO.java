package com.like.board.domain.repository.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleResponseDTO {
		
	LocalDateTime sysDt;	
	
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
	
	long pkArticle;	
	
	/**
	 * 게시글 상위키
	 */	
	long ppkArticle;		
	
	/**
	 * 제목
	 */	
	String title;
    
    /**
     * 내용
     */	
    String contents;
     
    /**
     * 비밀번호
     */	
    String pwd;
    
    /**
     * 조회 수
     */	
    int hitCount;
        
    /**
     * 시작일자
     */	
    LocalDate fromDate;
    
    /**
     * 종료일자
     */	
    LocalDate toDate;
    
    /**
     * 출력순서
     */	
    int seq;
    
    /**
     * 게층 횟수
     */	
    int depth;    
	
	/**
	 * 게시판 외래키
	 */
    long fkBoard;
			
	@QueryProjection
	public ArticleResponseDTO(LocalDateTime sysDt, String sysUser, LocalDateTime updDt, String updUser, long pkArticle,
			long ppkArticle, String title, String contents, String pwd, int hitCount, LocalDate fromDate,
			LocalDate toDate, int seq, int depth, long fkBoard) {
		super();
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.updDt = updDt;
		this.updUser = updUser;
		this.pkArticle = pkArticle;
		this.ppkArticle = ppkArticle;
		this.title = title;
		this.contents = contents;
		this.pwd = pwd;
		this.hitCount = hitCount;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = seq;
		this.depth = depth;
		this.fkBoard = fkBoard;
	}
				             
}
