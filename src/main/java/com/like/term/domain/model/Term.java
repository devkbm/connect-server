package com.like.term.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cmterm")
@EntityListeners(AuditingEntityListener.class)
public class Term extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -206378092418320228L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_term")
	Long pkTerm;	

	@Column(name="name")
	String nameKor;
		
	@Column(name="abbr")
	String abbreviation;
	
	@Column(name="name_eng")
	String nameEng;
		
	@Column(name="abbr_eng")
	String abbreviationEng;
	
	@Column(name="detail")
	String detail;
	
	@Column(name="cmt")
	String comment;
	
	protected Term() {}

	@Override
	public String toString() {
		return "Term [pkTerm=" + pkTerm + ", nameKor=" + nameKor + ", abbreviation=" + abbreviation + ", nameEng="
				+ nameEng + ", abbreviationEng=" + abbreviationEng + ", detail=" + detail + ", comment=" + comment
				+ "]";
	}
	
}
