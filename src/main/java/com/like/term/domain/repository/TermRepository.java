package com.like.term.domain.repository;

import java.util.List;

import com.like.term.domain.model.Term;
import com.like.term.domain.repository.dto.TermQueryDTO;

public interface TermRepository {
	
	Term getTerm(Long pkTerm);	
	
	List<Term> getTermList();
	
	List<Term> getTermList(TermQueryDTO termQueryDTO);
	
	void saveTerm(Term term);
	
	void saveTerm(List<Term> termList);
	
	void deleteTerm(Long pkTerm);
	
	void deleteTerm(List<Term> termList);
}
