package com.like.file.domain.model;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@AllArgsConstructor
@NoArgsConstructor
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
		
}
