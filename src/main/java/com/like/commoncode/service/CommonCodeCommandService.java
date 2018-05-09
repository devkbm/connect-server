package com.like.commoncode.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.infra.jparepository.CodeJpaRepository;
import com.like.commoncode.web.dto.CodeComboDTO;

@Service
@Transactional
public class CommonCodeCommandService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
			
	public void saveCodeGroup(CodeGroup codeGroup) {
		codeJpaRepository.saveCodeGroup(codeGroup);		
	}

	public void deleteCodeGroup(String codeGroup) {
		codeJpaRepository.deleteCodeGroup(codeGroup);		
	}
	
	public void saveCode(Code code) {		
		codeJpaRepository.saveCode(code);		
	}

	public void deleteCode(CommonCodeId commonCodeId) {
		codeJpaRepository.deleteCode(commonCodeId);		
	}
}
