package com.like.term.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.term.domain.model.QTermDictionary;
import com.like.term.domain.model.TermDictionary;
import com.like.term.domain.repository.TermRepository;
import com.like.term.dto.TermDTO;
import com.like.term.infra.jparepository.springdata.JpaTerm;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermJpaRepository implements TermRepository {
				
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaTerm jpaTerm;

	private final QTermDictionary qTermDictionary = QTermDictionary.termDictionary;
	
	@Override
	public TermDictionary getTerm(Long pkTerm) {
		return jpaTerm.findOne(pkTerm);
	}
	
	@Override
	public List<TermDictionary> getTermList() {
		return jpaTerm.findAll();
	}
	
	@Override
	public List<TermDictionary> getTermList(TermDTO.QueryCondition condition) {									
		return queryFactory.selectFrom(qTermDictionary)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public void saveTerm(TermDictionary term) {
		jpaTerm.save(term);			
	}

	@Override
	public void saveTerm(List<TermDictionary> termList) {
		jpaTerm.save(termList);		
	}

	@Override
	public void deleteTerm(Long pkTerm) {
		jpaTerm.delete(pkTerm);		
	}

	@Override
	public void deleteTerm(List<TermDictionary> termList) {
		jpaTerm.delete(termList);
	}
				
	
}