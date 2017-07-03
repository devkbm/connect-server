package com.like.code.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.QCommonCode;
import com.like.code.domain.model.QCommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
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
	public CommonCodeGroup getCodeGroup(String codeGroup) {
		return queryFactory				
				.selectFrom(qCommonCodeGroup)
				.where(qCommonCodeGroup.codeGroup.eq(codeGroup))
				.fetchOne();				
	}

	@Override
	public List<CommonCodeGroup> getCodeGroupList(String likeCodeGroupName) {
		return queryFactory				
				.selectFrom(qCommonCodeGroup)
				.where(qCommonCodeGroup.codeGroupName.like("%"+likeCodeGroupName+"%"))
				.fetch();
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
	public void deleteCodeGroup(String codeGroup) {
		jpaCommonCodeGroup.delete(codeGroup);		
	}

	@Override
	public CommonCode getCode(CommonCodeId commonCodeId) {
		return jpaCommonCode.findOne(commonCodeId);
	}

	@Override
	public List<CommonCode> getCodeList(String codeGroup) {		
		return queryFactory
				.selectFrom(qCommonCode)
				.where(qCommonCode.commonCodeGroup.codeGroup.eq(codeGroup))
				.fetch();
	}

	@Override
	public void saveCode(CommonCode code) {		
		if (code.getCommonCodeGroup() == null ) {
			CommonCodeGroup codeGroup =jpaCommonCodeGroup.findOne(code.getId().getCodeGroup());
			code.setCommonCodeGroup(codeGroup);
		}
			 
		jpaCommonCode.save(code);		
	}

	@Override
	public void deleteCode(CommonCodeId commonCodeId) {
		jpaCommonCode.delete(commonCodeId);		
	}
					
	
}