package com.like.commoncode.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.QCode;
import com.like.commoncode.domain.model.QCodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.domain.repository.CommonCodeRepository;
import com.like.commoncode.infra.jparepository.springdata.JpaCommonCode;
import com.like.commoncode.infra.jparepository.springdata.JpaCommonCodeGroup;
import com.like.commoncode.web.dto.CodeComboDTO;
import com.like.commoncode.web.dto.CodeDTO;
import com.like.commoncode.web.dto.CodeGroupQueryDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CodeJpaRepository implements CommonCodeRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaCommonCodeGroup jpaCommonCodeGroup;
	
	@Autowired
	private JpaCommonCode jpaCommonCode;
	
	private final QCodeGroup qCommonCodeGroup = QCodeGroup.codeGroup1;	
	private final QCode qCommonCode = QCode.code;
	
	@Override
	public CodeGroup getCodeGroup(String codeGroup) {
		return queryFactory				
				.selectFrom(qCommonCodeGroup)
				.where(qCommonCodeGroup.codeGroup.eq(codeGroup))
				.fetchOne();				
	}

	@Override	
	public List<CodeGroup> getCodeGroupList(CodeGroupQueryDTO commonCodeGroupQueryDTO) {
		return queryFactory				
				.selectFrom(qCommonCodeGroup)				
				.where(commonCodeGroupQueryDTO.getQuerySpec())
				.fetch();
	}

	@Override
	public List<CodeGroup> getCodeGroupList() {
		return jpaCommonCodeGroup.findAll();
	}

	@Override
	public void saveCodeGroup(CodeGroup codeGroup) {
		jpaCommonCodeGroup.save(codeGroup);		
	}

	@Override
	public void deleteCodeGroup(String codeGroup) {
		jpaCommonCodeGroup.delete(codeGroup);		
	}

	@Override
	public Code getCode(CommonCodeId commonCodeId) {
		return jpaCommonCode.findOne(commonCodeId);
	}

	@Override
	public List<Code> getCodeList(String codeGroup) {		
		return queryFactory
				.selectFrom(qCommonCode)
				.where(qCommonCode.commonCodeGroup.codeGroup.eq(codeGroup))
				.fetch();
	}
	
	
	@Override
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {

		return queryFactory
				.select(Projections.constructor(CodeComboDTO.class, qCommonCode.id.code,qCommonCode.codeName,qCommonCode.codeNameAbbreviation))
				.from(qCommonCode)
				.where(qCommonCode.id.codeGroup.eq(codeGroup))
				.fetch();
	}

	@Override
	public void saveCode(Code code) {		
		if (code.getCommonCodeGroup() == null ) {
			
			CodeGroup codeGroup =jpaCommonCodeGroup.findOne(code.getId().getCodeGroup());
			code.setCommonCodeGroup(codeGroup);
		}
			 
		jpaCommonCode.save(code);		
	}

	@Override
	public void deleteCode(CommonCodeId commonCodeId) {
		jpaCommonCode.delete(commonCodeId);		
	}
					
	
}