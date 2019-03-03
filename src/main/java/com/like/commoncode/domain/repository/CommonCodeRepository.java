package com.like.commoncode.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.web.dto.CodeComboDTO;

@Repository
public interface CommonCodeRepository {		
	
	Code getCode(String codeId);
	
	List<Code> getCodeList(String parentCode);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
	
	void saveCode(Code code);
	
	void deleteCode(String codeId);	
}
