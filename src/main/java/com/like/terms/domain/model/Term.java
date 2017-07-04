package com.like.terms.domain.model;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "cmterms")
@EntityListeners(AuditingEntityListener.class)
public class Term {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_terms")
	Long pkTerms;	

	@Column(name="term")
	String term;
		
	@Column(name="abbr")
	String abbreviation;
	
	@Column(name="term_eng")
	String termEng;
		
	@Column(name="abbr_eng")
	String abbreviationEng;
	
	@Column(name="desc")
	String description;
	
	@Column(name="cmt")
	String comment;
}
