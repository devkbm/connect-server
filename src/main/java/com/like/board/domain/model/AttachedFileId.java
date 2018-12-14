package com.like.board.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AttachedFileId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="PK_ARTICLE", nullable=false, updatable=false)
	private Long pkArticle;
	
	@Column(name="PK_FILE", nullable=false, updatable=false)
	private Long pkFile;

	protected AttachedFileId() {}
	
	public AttachedFileId(Long pkArticle, Long pkFile) {		
		this.pkArticle = pkArticle;
		this.pkFile = pkFile;
	}	
	
	
}