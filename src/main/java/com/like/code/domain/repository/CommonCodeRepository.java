package com.like.code.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.domain.repository.dto.CommonCodeComboDTO;

@Repository
public interface CommonCodeRepository {
	
	CommonCodeGroup getCodeGroup(String codeGroup);
	
	List<CommonCodeGroup> getCodeGroupList();
	
	List<CommonCodeGroup> getCodeGroupList(String codeGroupName);
	
	void saveCodeGroup(CommonCodeGroup codeGroup);
	
	void deleteCodeGroup(String codeGroup);
	
	
	CommonCode getCode(CommonCodeId commonCodeId);
	
	List<CommonCode> getCodeList(String codeGroup);
	
	List<CommonCodeComboDTO> getCodeListByComboBox(String codeGroup);
	
	void saveCode(CommonCode code);
	
	void deleteCode(CommonCodeId commonCodeId);	
}