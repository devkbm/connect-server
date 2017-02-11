package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



import com.fasterxml.jackson.annotation.*;
import com.like.common.domain.AuditEntity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"articles"})
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
    String ppkBoard;
	
	/**
	 * 게시판_타입
	 */
	@Column(name="board_type")
    String boardType;
	
	/**
     * 게시판 명
     */
	@Column(name="board_name")
    String boardNm;             
    
    /**
     * 게시판_설명
     */
	@Column(name="board_desc")
    String boardDesc;
    
    /**
     * 시작일자
     */	
	@Column(name="from_dt")
	@JsonFormat(pattern = "dd::MM::yyyy")
	LocalDate fromDt;
    
    /**
     * 종료일자
     */	
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")	
	@Column(name="to_dt")
    LocalDate toDt;    
    
    /**
     * 사용여부
     */
	@Column(name="use_yn")
    String useYn;
    
    /**
     * 게시글 갯수
     */
	@Column(name="article_cnt")
    long articleCnt;
    
    /**
     * 출력순서
     */
	@Column(name="seq")
    long seq;

    @OneToMany(mappedBy = "board")    
    @JsonManagedReference
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)        
    List<Article> articles = new ArrayList<Article>();
    
    public Board() {}
    
    public Board(String boardNm) {
    	this.boardNm = boardNm;
    	this.ppkBoard = "root";
    	this.sysUser = "test";
    	this.updUser = "test";
    	this.boardType = "A1";
    	this.fromDt = LocalDate.now();
    	this.toDt = LocalDate.now();    	    	
    }
    
    public boolean hasParentBoard() {    	    		    
		//return StringUtils.isEmpty(this.ppkBoard) ? false : true;
    	return false;
	}
    
    public void setParentRoot() {
    	this.ppkBoard = "root";
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


	public Long getPkBoard() {
		return pkBoard;
	}

	public String getPpkBoard() {
		return ppkBoard;
	}

	public String getBoardType() {
		return boardType;
	}

	public String getBoardNm() {
		return boardNm;
	}

	public String getBoardDesc() {
		return boardDesc;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public String getUseYn() {
		return useYn;
	}

	public long getArticleCnt() {
		return articleCnt;
	}

	public long getSeq() {
		return seq;
	}

	public List<Article> getArticles() {
		return articles;
	}

	@Override
	public String toString() {
		return "Board [pkBoard=" + pkBoard + ", ppkBoard=" + ppkBoard + ", boardType=" + boardType + ", boardNm="
				+ boardNm + ", boardDesc=" + boardDesc + ", fromDt=" + fromDt + ", toDt=" + toDt + ", useYn=" + useYn
				+ ", articleCnt=" + articleCnt + ", seq=" + seq + "]";
	}	
	
	
}
