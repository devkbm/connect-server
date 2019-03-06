package com.like.dept.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.like.dept.domain.model.QDept;
import com.querydsl.core.BooleanBuilder;

import lombok.Builder;
import lombok.Data;

public class DeptDTO {
	
	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QDept qDept = QDept.dept;
		
		String deptCode;
				
		String deptName;
					
		Boolean isEnabled;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			if (StringUtils.hasText(this.deptCode)) {
				builder.and(qDept.deptCode.like("%"+this.deptCode+"%"));
			}
			
			if (StringUtils.hasText(this.deptName)) {
				builder.and(qDept.deptName.like("%"+this.deptName+"%"));
			}			
			
			return builder;
		}
	}
	
	@Data
	@Builder	
	public static class DeptSave implements Serializable {
		
		private static final long serialVersionUID = -670038546212531439L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String parentDeptCode;
		
		@NotEmpty(message="부서코드는 필수 입력 사항입니다.")
		String deptCode;		
		
		@NotEmpty(message="부서명은 필수 입력 사항입니다.")
		String deptName;		
							
		LocalDate fromDate;
				
		LocalDate toDate;
		
		Integer seq;
	}
	
	
}
