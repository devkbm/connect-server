package com.like.commoncode.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.domain.repository.dto.CodeComboDTO;
import com.like.commoncode.domain.repository.dto.CodeGroupQueryDTO;

@Repository
public interface CommonCodeRepository {
	
	CodeGroup getCodeGroup(String codeGroup);
	
	List<CodeGroup> getCodeGroupList();
	
	List<CodeGroup> getCodeGroupList(CodeGroupQueryDTO commonCodeGroupQueryDTO);
	
	void saveCodeGroup(CodeGroup codeGroup);
	
	void deleteCodeGroup(String codeGroup);
	
	
	Code getCode(CommonCodeId commonCodeId);
	
	List<Code> getCodeList(String codeGroup);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
	
	void saveCode(Code code);
	
	void deleteCode(CommonCodeId commonCodeId);	
}
