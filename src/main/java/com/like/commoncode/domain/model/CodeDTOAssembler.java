package com.like.commoncode.domain.model;

import com.like.commoncode.dto.CodeDTO;
import com.like.commoncode.dto.CodeDTO.CodeSave;

public class CodeDTOAssembler {	
		
	public static Code createEntity(CodeDTO.CodeSave dto, Code parentCode) {
		/*if (dto.getId() == null) {
			new IllegalArgumentException("ID가 존재하지 않습니다.");
		}*/
		
		return Code.builder()				
				.parentCode(parentCode)
				.code(dto.getCode())
				.codeName(dto.getCodeName())
				.codeNameAbbreviation(dto.getCodeNameAbbreviation())
				.useYn(dto.isUseYn())
				.fromDate(dto.getFromDate())
				.toDate(dto.getToDate())
				.seq(dto.getSeq())
				.fixedLengthYn(dto.isFixedLengthYn())
				.codeLength(dto.getCodeLength())
				.build();
	}
	
	public static Code mergeEntity(Code entity, CodeDTO.CodeSave dto) {
		
		/*
		dept.deptName 	= nvl(dto.getDeptName(), 	dept.deptName);
		dept.isUse		= nvl(dto.getIsUse(), 		dept.isUse);
		dept.fromDate	= nvl(dto.getFromDate(), 	dept.fromDate);
		dept.toDate		= nvl(dto.getToDate(),		dept.toDate);
		*/
		
		return entity;
	}	
	
	public static CodeDTO.CodeSave convertDTO(Code entity) {					
		
		CodeSave dto = CodeSave.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.id(entity.id)
								.parentId(entity.parentCode == null ? null : entity.parentCode.id)
								.code(entity.code)
								.codeName(entity.codeName)
								.codeNameAbbreviation(entity.codeNameAbbreviation)
								.useYn(entity.useYn)
								.fromDate(entity.fromDate)
								.toDate(entity.toDate)
								.seq(entity.seq)
								.fixedLengthYn(entity.fixedLengthYn)
								.codeLength(entity.codeLength)
								.build();		
		return dto;
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
