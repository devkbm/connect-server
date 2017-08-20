package com.like.term.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.term.domain.model.Term;
import com.like.term.domain.repository.TermRepository;
import com.like.term.domain.repository.dto.TermQueryDTO;
import com.like.term.infra.jparepository.springdata.JpaTerm;
import com.like.term.domain.model.QTerm;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermJpaRepository implements TermRepository {
				
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaTerm jpaTerm;

	private final QTerm qTerm = QTerm.term;
	
	@Override
	public Term getTerm(Long pkTerm) {
		return jpaTerm.findOne(pkTerm);
	}
	
	@Override
	public List<Term> getTermList() {
		return jpaTerm.findAll();
	}
	
	@Override
	public List<Term> getTermList(TermQueryDTO requestDTO) {									
		return queryFactory.selectFrom(qTerm)
				.where(requestDTO.getQuerySpec())
				.fetch();
	}

	@Override
	public void saveTerm(Term term) {
		jpaTerm.save(term);			
	}

	@Override
	public void saveTerm(List<Term> termList) {
		jpaTerm.save(termList);		
	}

	@Override
	public void deleteTerm(Long pkTerm) {
		jpaTerm.delete(pkTerm);		
	}

	@Override
	public void deleteTerm(List<Term> termList) {
		jpaTerm.delete(termList);
	}
				
	
}