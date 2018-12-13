package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.board.domain.model.enums.PasswordType;
import com.like.board.dto.ArticleSaveDTO;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"board","articleChecks"})
@Getter
@Entity
@Table(name = "GRWARTICLE")
@EntityListeners(AuditingEntityListener.class)
public class Article extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -6554446667428677065L;
	
	/**
	 * 게시글 키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ARTICLE")
	Long pkArticle;	
	
	/**
	 * 게시글 상위키
	 */
	@Column(name="PPK_ARTICLE")
	Long ppkArticle;		
	
	
	@Formula("(SELECT X.USER_NAME FROM COM.COMUSER X WHERE X.USER_ID = sys_user)")
	private String userName;
	
	/**
	 * 제목
	 */
	@NotEmpty(message="제목은 필수 입력 사항입니다.")
	@Column(name="TITLE")
	String title;
    
    /**
     * 내용
     */
	@Column(name="CONTENTS")
    String contents;        
    
    /**
     * 조회 수
     */
	@Column(name="HIT_CNT")
    int hitCount;
        
    /**
     * 시작일자
     */
	@Column(name="FROM_DT")
    LocalDate fromDate;
    
    /**
     * 종료일자
     */
	@Column(name="TO_DT")
    LocalDate toDate;
    
    /**
     * 출력순서
     */
	@Column(name="SEQ")
    Integer seq;
    
    /**
     * 게층 횟수
     */
	@Column(name="HIER_DEPTH")
    int depth;
	
	@Column(name="PWD_YN")
	private Boolean pwdYn;
	
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="HASH_METHOD")
	private PasswordType pwdMethod;
	
	/**
     * 비밀번호
     */
	@Column(name="PWD")
    String pwd;
    
	/**
	 * 게시판 외래키
	 */           
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_BOARD", nullable=false, updatable=false)
	Board board;
    
    @OneToMany(mappedBy = "article")
    List<ArticleCheck> articleChecks = new ArrayList<ArticleCheck>();
        
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="GRWARTICLEFILES",
    		joinColumns= @JoinColumn(name="pk_article",  nullable=false, updatable=false ),
    		inverseJoinColumns=@JoinColumn(name="pk_file",  nullable=false, updatable=false))
    private List<FileInfo> files = new ArrayList<FileInfo>();       
        
    @OneToMany(mappedBy = "board")
    private List<AttachedFile> files2 = new ArrayList<>();
    
	protected Article() {}
	
	public Article(Board board, String title, String contents) {
		this.board = board;
		this.title = title;
		this.contents = contents;
		this.fromDate = LocalDate.now();
		this.toDate = LocalDate.of(9999, Month.DECEMBER, 31);				
	}
	
	public Article(Board board, String title, String contents, LocalDate fromDate, LocalDate toDate) {
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