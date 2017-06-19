package com.like.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.infra.jparepository.CodeJpaRepository;

@Service("commonCodeService")
@Transactional
public class CommonCodeService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
		
	public CommonCodeGroup getCodeGroup(String codeGroup) {
		return codeJpaRepository.getCodeGroup(codeGroup);
	}

	public List<CommonCodeGroup> getCodeGroupList(String likeCodeGroupName) {
		return codeJpaRepository.getCodeGroupList(likeCodeGroupName);
	}
	
	public List<CommonCodeGroup> getCodeGroupList() {
		return codeJpaRepository.getCodeGroupList();
	}
	
	public void saveCodeGroup(CommonCodeGroup codeGroup) {
		codeJpaRepository.saveCodeGroup(codeGroup);		
	}

	public void deleteCodeGroup(String codeGroup) {
		codeJpaRepository.deleteCodeGroup(codeGroup);		
	}

	public CommonCode getCode(CommonCodeId commonCodeId) {
		return codeJpaRepository.getCode(commonCodeId);
	}

	public List<CommonCode> getCodeList(String codeGroup) {		
		return codeJpaRepository.getCodeList(codeGroup);
	}

	public void saveCode(CommonCode code) {		
		codeJpaRepository.saveCode(code);		
	}

	public void deleteCode(CommonCodeId commonCodeId) {
		codeJpaRepository.deleteCode(commonCodeId);		
	}
}
