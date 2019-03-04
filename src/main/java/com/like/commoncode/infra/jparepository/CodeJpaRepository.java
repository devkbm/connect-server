package com.like.commoncode.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.QCode;
import com.like.commoncode.domain.repository.CommonCodeRepository;
import com.like.commoncode.infra.jparepository.springdata.JpaCommonCode;
import com.like.commoncode.web.dto.CodeComboDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CodeJpaRepository implements CommonCodeRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;	
	
	@Autowired
	private JpaCommonCode jpaCommonCode;
		
	private final QCode qCommonCode = QCode.code1;
		

	@Override
	public Code getCode(String codeId) {
		return jpaCommonCode.findOne(codeId);
	}
	
	@Override
	public List<Code> getAllCodeList() {
		return queryFactory
				.selectFrom(qCommonCode)				
				.fetch();
	}

	@Override
	public List<Code> getCodeList(String parentCodeId) {		
		return queryFactory
				.selectFrom(qCommonCode)
				.where(qCommonCode.parentCode.id.eq(parentCodeId))
				.fetch();
	}
	
	
	@Override
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {

		return null; /* queryFactory
				.select(Projections.constructor(CodeComboDTO.class, qCommonCode.id.code,qCommonCode.codeName,qCommonCode.codeNameAbbreviation))
				.from(qCommonCode)
				.where(qCommonCode.id.codeGroup.eq(codeGroup))
				.fetch();*/
	}

	@Override
	public void saveCode(Code code) {				
			 
		jpaCommonCode.save(code);		
	}

	@Override
	public void deleteCode(String codeId) {
		jpaCommonCode.delete(codeId);		
	}
					
	
}