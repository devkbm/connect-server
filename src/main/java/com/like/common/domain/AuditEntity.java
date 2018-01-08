package com.like.common.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {
	/**
	 * 생성일자
	 */
	@CreatedDate
	@Column(name = "sys_dt", nullable = false, updatable = false)	
	protected LocalDateTime sysDt;	
	
	/**
	 * 생성유저		
	 */
	@CreatedBy
	@Column(name = "sys_user", nullable = false, updatable = false)
	protected String sysUser;
	
	/**
	 * 수정일자			
	 */
	@LastModifiedDate
	@Column(name = "upd_dt")
	protected LocalDateTime updDt;
	
	/**
	 * 수정유저
	 */
	@LastModifiedBy
	@Column(name = "upd_user")
	protected String updUser;

	public LocalDateTime getSysDt() {
		return sysDt;
	}

	public String getSysUser() {
		return sysUser;
	}

	public LocalDateTime getUpdDt() {
		return updDt;
	}

	public String getUpdUser() {
		return updUser;
	}
	
	@Override
	public String toString() {
		return "AuditEntity [sysDt=" + sysDt + ", sysUser=" + sysUser + ", updDt=" + updDt + ", updUser=" + updUser
				+ "]";
	}
		
}
