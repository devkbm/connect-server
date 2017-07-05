package com.like.term.domain.repository;

import java.util.List;

import com.like.term.domain.model.Term;

public interface TermRepository {
	
	Term getTerm(Long pkTerm);	
	
	List<Term> getTermList();
	
	void saveTerm(Term term);
	
	void saveTerm(List<Term> termList);
	
	void deleteTerm(Long pkTerm);
}
