package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.board.domain.model.enums.PasswordType;
import com.like.board.dto.ArticleDTO;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"board","articleChecks","files"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
                          
    @OneToMany(mappedBy = "article", cascade=CascadeType.ALL)
    private List<AttachedFile> files = new ArrayList<>();    
		
	@Builder
	public Article(Board board, Long ppkArticle, String title, String contents,
			LocalDate fromDate, LocalDate toDate, Boolean pwdYn, PasswordType pwdMethod,
			String pwd) {		
		this.board = board;		
		this.ppkArticle = ppkArticle;		
		this.title = title;
		this.contents = contents;		
		this.fromDate = fromDate;
		this.toDate = toDate;				
		this.pwdYn = pwdYn;
		this.pwdMethod = pwdMethod;
		this.pwd = pwd;			
	}	
	
	public Article updateEntity(ArticleDTO.ArticleSave dto) {
		
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
			
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void updateHitCnt() {
		this.hitCount = this.hitCount + 1;	
	}	
		
	public List<FileInfo> getAttachedFileInfoList() {
		return this.files.stream()						 
				  		 .map(v -> v.fileInfo)
				  		 .collect(Collectors.toList());		
				  
	}	
		
}