package com.like.board.infra.mapper.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ArticleListDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6343198961752805944L;

	/**
	 * 생성일자
	 */
	Date sysDt;	
	
	/**
	 * 생성유저		
	 */
	String sysUser;
	
	/**
	 * 생성유저명
	 */
	String sysUsernm;
	
	/**
	 * 수정일자			
	 */
	Date updDt;
	
	/**
	 * 수정유저			
	 */
	String updUser;	
	
	/**
	 * 게시글 키
	 */
	String pkArticle;	
	
	/**
	 * 게시글 상위키
	 */
	String ppkArticle;		
	
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
    String hitCnt;    
        
    /**
     * 시작일자
     */
    String fromDt;
    
    /**
     * 종료일자
     */
    String toDt;
        
    /**
     * 출력순서
     */
    String seq;
    
    /**
     * 조회 여부
     */
    String checkYn;
    
    /**
     * 파일 첨부 유무
     */
    String fileYn;
    
    /**
     * 파일 정보
     * uuid | path | name | size
     */
    String fileInfo;
    
	/**
	 * 게시판 외래키
	 */
	String fkBoard;
		               
}
