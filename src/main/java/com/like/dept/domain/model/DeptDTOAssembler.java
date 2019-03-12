package com.like.dept.domain.model;

import com.like.dept.dto.DeptDTO;
import com.like.dept.dto.DeptDTO.DeptSave;

public class DeptDTOAssembler {	
		
	public static Dept createEntity(DeptDTO.DeptSave dto, Dept parentDept) {
		if (dto.getDeptCode() == null) {
			new IllegalArgumentException("부서코드가 없습니다.");
		}
		
		return Dept.builder()
				.deptCode(dto.getDeptCode())
				.deptNameKorean(dto.getDeptName())
				.parentDept(parentDept)
				.fromDate(dto.getFromDate())
				.toDate(dto.getToDate())
				.seq(dto.getSeq())
				.parentDept(parentDept)
				.build();
	}
	
	public static Dept mergeEntity(Dept dept, DeptDTO.DeptSave dto, Dept parentDept) {
		
		if (dept == null)
			throw new IllegalArgumentException("Dept 엔티티가 존재하지 않습니다.");
		
		dept.deptNameKorean 	= nvl(dto.getDeptName(), 	dept.deptNameKorean);		
		dept.toDate		= nvl(dto.getToDate(),		dept.toDate);
		
		if (parentDept != null) {
			dept.parentDept = parentDept; 
		}
		
		return dept;
	}	
	
	public static DeptDTO.DeptSave convertDTO(Dept dept) {					
		
		DeptSave dto = DeptSave.builder()
								.createdDt(dept.getCreatedDt())
								.createdBy(dept.getCreatedBy())
								.modifiedDt(dept.getModifiedDt())
								.modifiedBy(dept.getModifiedBy())
								.deptCode(dept.deptCode)
								.deptName(dept.deptNameKorean)													
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
