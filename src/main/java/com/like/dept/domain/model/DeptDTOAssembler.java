package com.like.dept.domain.model;

import com.like.dept.dto.DeptDTO;
import com.like.dept.dto.DeptDTO.DeptSave;

public class DeptDTOAssembler {	
		
	public static Dept createEntity(DeptDTO.DeptSave dto) {
		if (dto.getDeptCode() == null) {
			new IllegalArgumentException("부서코드가 없습니다.");
		}
		
		return Dept.builder()
				.deptCode(dto.getDeptCode())
				.deptName(dto.getDeptName())
				.isUse(dto.getIsUse())
				.build();
	}
	
	public static Dept mergeEntity(Dept dept, DeptDTO.DeptSave dto) {
		
		dept.deptName 	= nvl(dto.getDeptName(), 	dept.deptName);
		dept.isUse		= nvl(dto.getIsUse(), 		dept.isUse);
		dept.fromDate	= nvl(dto.getFromDate(), 	dept.fromDate);
		dept.toDate		= nvl(dto.getToDate(),		dept.toDate);
		
		return dept;
	}	
	
	public static DeptDTO.DeptSave convertDTO(Dept dept) {					
		
		DeptSave dto = DeptSave.builder()
								.createdDt(dept.getCreatedDt())
								.createdBy(dept.getCreatedBy())
								.modifiedDt(dept.getModifiedDt())
								.modifiedBy(dept.getModifiedBy())
								.deptCode(dept.deptCode)
								.deptName(dept.deptName)
								.isUse(dept.isUse)
								.fromDate(dept.fromDate)
								.toDate(dept.toDate)
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
