package com.like.common.file.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;

@JsonAutoDetect
@Entity
@Table(name = "cmfileinfo")
@EntityListeners(AuditingEntityListener.class)
public class FileInfo extends AuditEntity implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 4108977246168878308L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_file")
	Long pkFile;	

	@Column(name="pgm_id")
	String pgmId;
		
	@Column(name="user_id")
	String userId;
	
	@Column(name="content_type")
	String contentType;
		
	@Column(name="uuid")
	String uuid;
	
	@Column(name="path")
	String path;
	
	@Column(name="file_nm")
	String fileNm;
	
	@Column(name="size")
	long size;
	
	@Column(name="download_cnt")
	long downloadCnt;	
	
	@Column(name="fk")
	String fk;

	public Long getPkFile() {
		return pkFile;
	}

	public void setPkFile(Long pkFile) {
		this.pkFile = pkFile;
	}

	public String getPgmId() {
		return pgmId;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getDownloadCnt() {
		return downloadCnt;
	}

	public void setDownloadCnt(long downloadCnt) {
		this.downloadCnt = downloadCnt;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}
		
}
