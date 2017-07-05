package com.like.term.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.term.domain.model.Term;
import com.like.term.domain.repository.TermRepository;

@Service("termService")
@Transactional
public class TermService {
	
    @Resource(name="termJpaRepository")
	private TermRepository termRepository;      
	
	public Term getTerm(Long pkTerm) {
		return termRepository.getTerm(pkTerm);
	}
	
	public List<Term> getTermList() {
		return termRepository.getTermList();
	}

	public void saveTerm(Term term) {
		termRepository.saveTerm(term);
	}
	
	public void saveTerm(List<Term> termList) {
		termRepository.saveTerm(termList);		
	}
	
	public void deleteTerm(Long pkTerm) {
		termRepository.deleteTerm(pkTerm);		
	}
		
}