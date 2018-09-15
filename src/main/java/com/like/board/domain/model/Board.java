package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.*;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.domain.model.enums.PasswordType;
import com.like.board.web.dto.BoardSaveDTO;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"articles","parent"})
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "GRWBOARD")
@EntityListeners(AuditingEntityListener.class)
public class Board extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -9079904581069883219L;
		
	/**
	 * 게시판 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_BOARD")
	private Long pkBoard;
	
    /**
     * 상위 게시판 키
     */
	@Column(name="PPK_BOARD")
    private Long ppkBoard;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PPK_BOARD", insertable=false, updatable=false )
	private Board parent;
	
	/**
	 * 게시판_타입
	 */
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="BOARD_TYPE")
    private BoardType boardType;
	
	/**
     * 게시판 명
     */	
	@Column(name="BOARD_NAME")
    private String boardName;             
    
    /**
     * 게시판_설명
     */
	@Column(name="BOARD_DESC")
	private String boardDescription;
    
    /**
     * 시작일자
     */	
	@Column(name="FROM_DT")
	private LocalDate fromDate;
    
    /**
     * 종료일자
     */	
	@Column(name="TO_DT")
	private LocalDate toDate;    
    
    /**
     * 사용여부
     */
	@Column(name="USE_YN")
	private Boolean useYn;
    
    /**
     * 게시글 갯수
     */
	@Column(name="ARTICLE_CNT")
	private long articleCount;
    
    /**
     * 출력순서
     */
	@Column(name="SEQ")
	private long sequence;	

    @OneToMany(mappedBy = "board")          
    List<Article> articles = new ArrayList<Article>();       
    
    public Board(String boardNm) {
    	this.boardName 	= boardNm;
    	    	
    	this.boardType 	= BoardType.BOARD;
    	this.useYn		= true;
    	this.fromDate 	= LocalDate.now();
    	this.toDate 	= LocalDate.of(9999, 12, 31);    	
    	this.articleCount=0;    	
    }
    
    public Board updateEntity(BoardSaveDTO dto) {
    	this.ppkBoard 			= dto.getPpkBoard();
    	this.boardName			= dto.getBoardName();
    	this.boardType 			= BoardType.valueOf(dto.getBoardType());
    	this.boardDescription 	= dto.getBoardDescription();
    	this.fromDate			= dto.getFromDate();
    	this.toDate				= dto.getToDate();
    	this.useYn				= dto.getUseYn();
    	this.articleCount		= dto.getArticleCount();
    	this.sequence			= dto.getSequence();		
    	
    	return this;
    }
    
    public boolean hasParentBoard() {    	    		    		
    	return this.pkBoard != this.ppkBoard ? true : false;
	}
    
    public void setParentRoot() {
    	this.ppkBoard = this.pkBoard;
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