package com.like.board.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class BoardDTO implements Serializable {

	private static final long serialVersionUID = 8495364239247192458L;

	String sysDt;
	
	String sysUser;
	
	String updDt;
	
	String updUser;
	
	String pkArticle;
	
	String ppkArticle;
	
	String fkBoard;
	
	String title;
	
	String contents;
	
	String pwd;
	
	LocalDate fromDt;
	
	LocalDate toDt;
	
	Long seq;
	
	int hitCnt;
	
	String sysUsernm;
	
	String checkYn;
	
	String fileYn;
	
	String fileInfo;
	
}