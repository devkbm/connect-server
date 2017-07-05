package com.like.term.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cmterm")
@EntityListeners(AuditingEntityListener.class)
public class Term {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_term")
	Long pkTerm;	

	@Column(name="name")
	String name;
		
	@Column(name="abbr")
	String abbreviation;
	
	@Column(name="name_eng")
	String nameEng;
		
	@Column(name="abbr_eng")
	String abbreviationEng;
	
	@Column(name="desc")
	String description;
	
	@Column(name="cmt")
	String comment;
}
