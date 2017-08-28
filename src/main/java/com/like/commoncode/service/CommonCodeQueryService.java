package com.like.commoncode.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.domain.repository.dto.CodeComboDTO;
import com.like.commoncode.domain.repository.dto.CodeGroupQueryDTO;
import com.like.commoncode.infra.jparepository.CodeJpaRepository;

@Service
@Transactional(readOnly=true)
public class CommonCodeQueryService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
		
	public CodeGroup getCodeGroup(String codeGroup) {
		return codeJpaRepository.getCodeGroup(codeGroup);
	}

	public List<CodeGroup> getCodeGroupList(CodeGroupQueryDTO commonCodeGroupQueryDTO) {
		return codeJpaRepository.getCodeGroupList(commonCodeGroupQueryDTO);
	}
	
	public List<CodeGroup> getCodeGroupList() {
		return codeJpaRepository.getCodeGroupList();
	}
	
	public Code getCode(CommonCodeId commonCodeId) {
		return codeJpaRepository.getCode(commonCodeId);
	}
		
	public List<Code> getCodeList(String codeGroup) {		
		return codeJpaRepository.getCodeList(codeGroup);
	}
	
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return codeJpaRepository.getCodeListByComboBox(codeGroup);
	}
	
}
