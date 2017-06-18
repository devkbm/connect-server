package com.like.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.infra.jparepository.CodeJpaRepository;

@Service("commonCodeService")
@Transactional
public class CommonCodeService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
	
	public CommonCodeGroup getCodeGroup(Long pkCodeGroup) {
		
		return codeJpaRepository.getCodeGroup(pkCodeGroup);
	}

	public List<CommonCodeGroup> getCodeGroupList() {

		return codeJpaRepository.getCodeGroupList();
	}

	
	public void saveCodeGroup(CommonCodeGroup codeGroup) {
		codeJpaRepository.saveCodeGroup(codeGroup);;
		
	}

	
	public void deleteCodeGroup(Long pkCodeGroup) {
		codeJpaRepository.deleteCodeGroup(pkCodeGroup);		
	}

	
	public CommonCode getCode(Long pkCode) {
		return codeJpaRepository.getCode(pkCode);
	}

	
	public List<CommonCode> getCodeList(Long fkCodeGroup) {
		
		return codeJpaRepository.getCodeList(fkCodeGroup);
	}

	
	public void saveCode(CommonCode code, Long fkCodeGroup) {
		codeJpaRepository.saveCode(code, fkCodeGroup);		
	}

	
	public void deleteCode(Long pkCode) {
		codeJpaRepository.deleteCode(pkCode);		
	}
}
