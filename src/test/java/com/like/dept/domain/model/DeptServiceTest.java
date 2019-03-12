package com.like.dept.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.dept.domain.model.Dept;
import com.like.dept.service.DeptService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DeptServiceTest {
		 
	@Autowired
	DeptService deptService;	
				
	@Test	
	public void test01_메뉴그룹등록및조회() {
		//Given
		Dept dept = Dept.builder()
						.deptCode("GAD")
						.deptNameKorean("총무팀")
						.deptAbbreviationKorean("총무팀")
						.deptNameEnglish("General Affairs Department")
						.deptAbbreviationEnglish("GAD")
						.fromDate(LocalDate.now())
						.toDate(LocalDate.of(9999, 12, 31))
						.seq(0)
						.comment("비고")
						.build();								
		//When
		deptService.saveDept(dept);
		
		//Then
		Dept test = deptService.getDept(dept.deptCode);
		
		assertThat(test.deptCode).isEqualTo("GAD");
		assertThat(test.deptNameKorean).isEqualTo("총무팀");
		assertThat(test.deptAbbreviationKorean).isEqualTo("총무팀");
		assertThat(test.deptNameEnglish).isEqualTo("General Affairs Department");
		assertThat(test.deptAbbreviationEnglish).isEqualTo("GAD");
		assertThat(test.fromDate).isEqualTo(LocalDate.now());
		assertThat(test.toDate).isEqualTo(LocalDate.of(9999, 12, 31));
		assertThat(test.seq).isEqualTo(0);						
		assertThat(test.comment).isEqualTo("비고");
	}
		
	
	
	
}
