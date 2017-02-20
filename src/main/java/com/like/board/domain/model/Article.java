package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.like.common.domain.AuditEntity;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"articleChecks","board"})
@Getter
@Entity
@Table(name = "grarticle")
@EntityListeners(AuditingEntityListener.class)
public class Article extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -6554446667428677065L;
	
	/**
	 * 게시글 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_article")
	Long pkArticle;	
	
	/**
	 * 게시글 상위키
	 */
	@Column(name="ppk_article")
	Long ppkArticle;		
	
	/**
	 * 제목
	 */
	@Column(name="title")
	String title;
    
    /**
     * 내용
     */
	@Column(name="contents")
    String contents;
     
    /**
     * 비밀번호
     */
	@Column(name="pwd")
    String pwd;
    
    /**
     * 조회 수
     */
	@Column(name="hit_cnt")
    int hitCnt;
        
    /**
     * 시작일자
     */
	@Column(name="from_dt")
    LocalDate fromDt;
    
    /**
     * 종료일자
     */
	@Column(name="to_dt")
    LocalDate toDt;
    
    /**
     * 출력순서
     */
	@Column(name="seq")
    Long seq;
    
    /**
     * 게층 횟수
     */
	@Column(name="depth")
    long depth;
    
	/**
	 * 게시판 외래키
	 */    
    @JsonBackReference    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_board", nullable=false, updatable=false)
	Board board;

    @JsonManagedReference
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks = new ArrayList<ArticleCheck>();
    
	public Article() {}    
	
	public boolean hasParentArticle() {		
		return this.ppkArticle != null ? true : false;
	}
	
	public void setBoard(Board board) {
		this.board = board;
		
		//무한루프에 빠지지 않도록 체크
		/*if (!board.getArticles().contains(this)) {
			board.getArticles().add(this);
		}*/
	}
	
	public void setParentPk(Long pk) {
		this.ppkArticle = pk;
	}
	
	public void setParentRoot() {
		this.ppkArticle = 0L;	
	}
	
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	
	public void updateHitCnt() {
		this.hitCnt = this.hitCnt + 1;	
	}
	
}