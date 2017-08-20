package com.like.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.domain.repository.dto.CommonCodeComboDTO;
import com.like.code.domain.repository.dto.CommonCodeGroupQueryDTO;
import com.like.code.infra.jparepository.CodeJpaRepository;

@Service
@Transactional(readOnly=true)
public class CommonCodeQueryService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
		
	public CommonCodeGroup getCodeGroup(String codeGroup) {
		return codeJpaRepository.getCodeGroup(codeGroup);
	}

	public List<CommonCodeGroup> getCodeGroupList(CommonCodeGroupQueryDTO commonCodeGroupQueryDTO) {
		return codeJpaRepository.getCodeGroupList(commonCodeGroupQueryDTO);
	}
	
	public List<CommonCodeGroup> getCodeGroupList() {
		return codeJpaRepository.getCodeGroupList();
	}
	
	public CommonCode getCode(CommonCodeId commonCodeId) {
		return codeJpaRepository.getCode(commonCodeId);
	}
		
	public List<CommonCode> getCodeList(String codeGroup) {		
		return codeJpaRepository.getCodeList(codeGroup);
	}
	
	public List<CommonCodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return codeJpaRepository.getCodeListByComboBox(codeGroup);
	}
	
}
