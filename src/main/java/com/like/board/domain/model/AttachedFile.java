package com.like.board.domain.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.file.domain.model.FileInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true, value = {"article"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWARTICLEFILE")
public class AttachedFile extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	AttachedFileId id;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PK_ARTICLE", insertable=false, updatable = false)
	Article article; 	
		
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PK_FILE", insertable=false, updatable = false)
	FileInfo fileInfo;
	
	public AttachedFile(Article article, FileInfo fileInfo) {
		this.id = new AttachedFileId(article.getPkArticle(), fileInfo.getPkFile());
		this.article = article;
		this.fileInfo = fileInfo;
	}	
		
}


