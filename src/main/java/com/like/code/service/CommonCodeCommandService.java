package com.like.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.domain.repository.dto.CommonCodeComboDTO;
import com.like.code.infra.jparepository.CodeJpaRepository;

@Service
@Transactional
public class CommonCodeCommandService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
			
	public void saveCodeGroup(CommonCodeGroup codeGroup) {
		codeJpaRepository.saveCodeGroup(codeGroup);		
	}

	public void deleteCodeGroup(String codeGroup) {
		codeJpaRepository.deleteCodeGroup(codeGroup);		
	}
	
	public void saveCode(CommonCode code) {		
		codeJpaRepository.saveCode(code);		
	}

	public void deleteCode(CommonCodeId commonCodeId) {
		codeJpaRepository.deleteCode(commonCodeId);		
	}
}
