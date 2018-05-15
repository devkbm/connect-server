package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.board.web.dto.ArticleSaveDTO;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"board","articleChecks","files"})
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
	@NotEmpty(message="제목은 필수 입력 사항입니다.")
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
    int hitCount;
        
    /**
     * 시작일자
     */
	@Column(name="from_dt")
    LocalDate fromDate;
    
    /**
     * 종료일자
     */
	@Column(name="to_dt")
    LocalDate toDate;
    
    /**
     * 출력순서
     */
	@Column(name="seq")
    Integer seq;
    
    /**
     * 게층 횟수
     */
	@Column(name="depth")
    int depth;
    
	/**
	 * 게시판 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_board", nullable=false, updatable=false)
	Board board;
    
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks = new ArrayList<ArticleCheck>();
    
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="grarticle_files",
    		joinColumns= @JoinColumn(name="pk_article"),
    		inverseJoinColumns=@JoinColumn(name="pk_file"))
    private List<FileInfo> files;       
    
	protected Article() {}
	
	public Article(String title, String contents) {
		this.title = title;
		this.contents = contents;
		this.fromDate = LocalDate.now();
		this.toDate = LocalDate.of(9999, Month.DECEMBER, 31);				
	}
	
	public Article(String title, String contents, LocalDate fromDate, LocalDate toDate) {
		this.title = title;
		this.contents = contents;
		this.fromDate = fromDate;
		this.toDate = toDate;				
	}
	
	public Article updateEntity(ArticleSaveDTO dto) {
		
		this.ppkArticle = dto.getPpkArticle();
		this.title 		= dto.getTitle();
		this.contents	= dto.getContents();
		this.pwd		= dto.getPwd();
		this.fromDate	= dto.getFromDate();
		this.toDate		= dto.getToDate();
		this.seq		= dto.getSeq();
		this.depth		= dto.getDepth();
		
		return this;
	}
	
	public void setBoard(Board board) {
		this.board = board;
		
		//무한루프에 빠지지 않도록 체크
		/*if (!board.getArticles().contains(this)) {
			board.getArticles().add(this);
		}*/
	}
	
	public boolean hasParentArticle() {		
		return this.ppkArticle != this.pkArticle ? true : false;
	}
		
	public void setParentPk(Long pk) {
		this.ppkArticle = pk;
	}
	
	public void setParentRoot() {
		this.ppkArticle = this.pkArticle;	
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void updateHitCnt() {
		this.hitCount = this.hitCount + 1;	
	}
	
	public boolean addAttachedFile(FileInfo file) {
		boolean rtn = false;
					
		if (this.files == null)
			this.files = new ArrayList<>();
		
		if (file != null) {
			rtn = files.add(file);			
		}			
			
		return rtn;
	}	
}