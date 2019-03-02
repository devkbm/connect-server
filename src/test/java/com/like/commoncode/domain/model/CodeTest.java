package com.like.commoncode.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.service.CommonCodeCommandService;
import com.like.commoncode.service.CommonCodeQueryService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CodeTest {	
	
	@Autowired
	CommonCodeCommandService commonCodeCommandService;
		
	@Autowired
	CommonCodeQueryService commonCodeQueryService;
	
	@Test
	public void test001_코드그룹등록및조회() {
		//Given
		CodeGroup codeGroup = this.createCodeGroup();
		
		//When		
		commonCodeCommandService.saveCodeGroup(codeGroup);
		
		CodeGroup test = commonCodeQueryService.getCodeGroup(codeGroup.getId());
		
		//Then
		assertThat(test.codeGroup).isEqualTo("A01");
		assertThat(test.codeGroupName).isEqualTo("테스트 코드 그룹");
		assertThat(test.fixedLengthYn).isEqualTo(true);
		assertThat(test.codeLength).isEqualTo(10);
		assertThat(test.cmt).isEqualTo("테스트 비고");						
	}
	
	@Test
	public void test002_코드등록및조회() {
		//Given
		CodeGroup codeGroup = this.createCodeGroup();
		commonCodeCommandService.saveCodeGroup(codeGroup);
		
		//When
		Code code = Code.builder()
						.id(new CommonCodeId(codeGroup.getId(), "AA"))
						.codeName("테스트코드")
						.codeNameAbbreviation("약어")
						.fromDate(LocalDate.of(2019,1,12).atStartOfDay())
						.toDate(LocalDate.of(9999, 12, 31).atStartOfDay())
						.cmt("테스트비고")
						.build();
		
		commonCodeCommandService.saveCode(code);
		
		Code test = commonCodeQueryService.getCode(code.getId());
		
		//Then
		assertThat(test.id).isEqualTo(new CommonCodeId(codeGroup.getId(), "AA"));
		assertThat(test.codeName).isEqualTo("테스트코드");
		assertThat(test.codeNameAbbreviation).isEqualTo("약어");
		assertThat(test.fromDate).isEqualTo(LocalDate.of(2019, 1, 12).atStartOfDay());
		assertThat(test.toDate).isEqualTo(LocalDate.of(9999, 12, 31).atStartOfDay());
		assertThat(test.cmt).isEqualTo("테스트비고");

		System.out.println(test.toString());
	}
	
	
	private CodeGroup createCodeGroup() {
		return CodeGroup.builder()
						.codeGroup("A01")
						.codeGroupName("테스트 코드 그룹")
					    .fixedLengthYn(true)
						.codeLength(10)
						.cmt("테스트 비고")
						.build();
	}
	
	
	
}
