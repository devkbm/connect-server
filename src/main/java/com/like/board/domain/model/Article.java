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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"board","articleChecks","files"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="pkArticle")
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
    Integer seq;
    
    /**
     * 게층 횟수
     */
	@Column(name="depth")
    int depth;
    
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
    
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="grarticle_files",
    		joinColumns= @JoinColumn(name="pk_article"),
    		inverseJoinColumns=@JoinColumn(name="pk_file"))
    private List<FileInfo> files;       
    
	protected Article() {}
	
	public Article(String title, String contents) {
		this.sysUser = "test";
		this.updUser = "test";
		this.title = title;
		this.contents = contents;
		this.fromDt = LocalDate.now();
		this.toDt = LocalDate.of(9999, Month.DECEMBER, 31);				
	}
	
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
	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void updateHitCnt() {
		this.hitCnt = this.hitCnt + 1;	
	}
	
	public boolean addAttachedFile(FileInfo file) {
		if (files == null)
			files = new ArrayList<>();
		return files.add(file);
	}
	
}