package com.like.terms.domain.repository;

import com.like.terms.domain.model.Term;

public interface termsRepository {
	
	Term getTerm(Long pkTerm);	
	
	void saveTerm(Term term);
}
