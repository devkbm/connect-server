package com.like.code.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;

@Repository
public interface CommonCodeRepository {

	CommonCodeGroup getCodeGroup(Long pkCodeGroup);
	
	List<CommonCodeGroup> getCodeGroupList();
	
	void saveCodeGroup(CommonCodeGroup codeGroup);
	
	void deleteCodeGroup(Long pkCodeGroup);
	
	
	CommonCode getCode(Long pkCode);
	
	List<CommonCode> getCodeList(Long fkCodeGroup);
	
	void saveCode(CommonCode code, Long fkCodeGroup);
	
	void deleteCode(Long pkCode);	
}
