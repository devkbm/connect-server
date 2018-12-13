package com.like.board.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.like.file.domain.model.FileInfo;

import lombok.Getter;

@Getter
@Entity
@Table(name = "GRWARTICLEFILES2")
public class AttachedFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ATTACH")
	Long pkAttach;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PK_ARTICLE", nullable=false, updatable=false)
	Article article; 	
	
	@OneToOne
	@JoinColumn(name="PK_FILE")
	FileInfo fileInfo;
	
	public AttachedFile(Article article, FileInfo fileInfo) {
		this.article = article;
		this.fileInfo = fileInfo;
	}
}
