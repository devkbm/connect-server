package com.like.code.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.QCommonCode;
import com.like.code.domain.model.QCommonCodeGroup;
import com.like.code.domain.repository.CommonCodeRepository;
import com.like.code.infra.jparepository.springdata.JpaCommonCode;
import com.like.code.infra.jparepository.springdata.JpaCommonCodeGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CodeJpaRepository implements CommonCodeRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaCommonCodeGroup jpaCommonCodeGroup;
	
	@Autowired
	private JpaCommonCode jpaCommonCode;
	
	private final QCommonCodeGroup qCommonCodeGroup = QCommonCodeGroup.commonCodeGroup;	
	private final QCommonCode qCommonCode = QCommonCode.commonCode;

	@Override
	public CommonCodeGroup getCodeGroup(Long pkCodeGroup) {
		
		return jpaCommonCodeGroup.findOne(pkCodeGroup);
	}

	@Override
	public List<CommonCodeGroup> getCodeGroupList() {

		return jpaCommonCodeGroup.findAll();
	}

	@Override
	public void saveCodeGroup(CommonCodeGroup codeGroup) {
		jpaCommonCodeGroup.save(codeGroup);
		
	}

	@Override
	public void deleteCodeGroup(Long pkCodeGroup) {
		jpaCommonCodeGroup.delete(pkCodeGroup);		
	}

	@Override
	public CommonCode getCode(Long pkCode) {
		return jpaCommonCode.findOne(pkCode);
	}

	@Override
	public List<CommonCode> getCodeList(Long fkCodeGroup) {
		
		return queryFactory
				.selectFrom(qCommonCode)
				.where(qCommonCode.codeGroup.pkCodeGroup.eq(fkCodeGroup))
				.fetch();
	}

	@Override
	public void saveCode(CommonCode code, Long fkCodeGroup) {
		CommonCodeGroup codeGroup = jpaCommonCodeGroup.findOne(fkCodeGroup);
		
		code.setCommonCodeGroup(codeGroup);
		
		jpaCommonCode.save(code);		
	}

	@Override
	public void deleteCode(Long pkCode) {
		jpaCommonCode.delete(pkCode);		
	}
					
	
}