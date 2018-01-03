package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.like.board.domain.model.enums.PasswordType;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"articles","parent"})
@Getter
@ToString()
@Table(name = "grboard")
@EntityListeners(AuditingEntityListener.class)
public class Board extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -9079904581069883219L;
		
	/**
	 * 게시판 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_board")
	Long pkBoard;
	
    /**
     * 상위 게시판 키
     */
	@Column(name="ppk_board")
    Long ppkBoard;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ppk_board", insertable=false, updatable=false )
	Board parent;
	
	/**
	 * 게시판_타입
	 */
	@Column(name="board_type")
    String boardType;
	
	/**
     * 게시판 명
     */	
	@Column(name="board_name")
    String boardName;             
    
    /**
     * 게시판_설명
     */
	@Column(name="board_desc")
    String boardDescription;
    
    /**
     * 시작일자
     */	
	@Column(name="from_dt")
	//@JsonFormat(pattern = "dd::MM::yyyy")
	LocalDate fromDate;
    
    /**
     * 종료일자
     */	
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")	
	@Column(name="to_dt")
    LocalDate toDate;    
    
    /**
     * 사용여부
     */
	@Column(name="use_yn")
    Boolean useYn;
    
    /**
     * 게시글 갯수
     */
	@Column(name="article_cnt")
    long articleCount;
    
    /**
     * 출력순서
     */
	@Column(name="seq")
    long sequence;
	
	@Column(name="pwd_yn")
	String pwdYn;
	
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="pwd_method")
	PasswordType pwdMethod;

    @OneToMany(mappedBy = "board")          
    List<Article> articles = new ArrayList<Article>();
    
    public Board() {}
    
    public Board(String boardNm) {
    	this.boardName = boardNm;
    	
    	this.sysUser = "test";
    	this.updUser = "test";
    	this.boardType = "A1";
    	this.fromDate = LocalDate.now();
    	this.toDate = LocalDate.now();    	    	
    }
    
    public boolean hasParentBoard() {    	    		    		
    	return this.parent != null ? true : false;
	}
    
    public void setParentRoot() {
    	//this.ppkBoard = 0L;
    }
    
    public void setParent(Board board) {
    	this.parent = board;
    }
    
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		this.articles.add(article);		
		/*if (article.getBoard() != this) {	// 무한루프에 빠지지 않도록 체크
			article.setBoard(this);
		}*;	*/
	}
	
}