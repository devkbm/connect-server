package com.like.board.domain.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;

@JsonAutoDetect
@Getter
@Entity
@Table(name = "grarticlecheck")
@EntityListeners(AuditingEntityListener.class)
public class ArticleCheck extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 6322358555393677284L;

	/**
	 * 개인별 게시글 조회  키
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_article_check")
	Long pkArticleCheck;	
	
	/**
	 * 조회수
	 */
	@Column(name="hit_cnt")
	int hitCnt;
        
	/**
	 * 게시판 외래키
	 */        
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_article", nullable=false, updatable=false)
	Article article;

	public ArticleCheck() {
		this.sysUser = "test";
		this.updUser = "test";
	}
			
	public void updateHitCnt() {
		this.hitCnt = this.hitCnt + 1;
	}
	
	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleCheck [pkArticleCheck=" + pkArticleCheck + ", hitCnt=" + hitCnt + ", article=" + article + "]";
	}
	
		
}
