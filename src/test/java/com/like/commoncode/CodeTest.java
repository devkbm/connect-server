package com.like.commoncode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.CodeGroup;
import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.commoncode.service.CommonCodeCommandService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CodeTest {

	private static final Logger log = LoggerFactory.getLogger(CodeTest.class);
	
	@Autowired
	CommonCodeCommandService cs;
			
	@Test
	public void 코드그룹추가() {
		CodeGroup codeGroup = new CodeGroup("A01","테스트 코드 그룹");
					
		cs.saveCodeGroup(codeGroup);
					
		log.info(codeGroup.toString());				
	}
	
	@Test
	public void 코드추가() {
		CodeGroup codeGroup = new CodeGroup("A01","테스트 코드 그룹");
		
		cs.saveCodeGroup(codeGroup);
						
		Code code = new Code(new CommonCodeId("A01", "AA"), "테스트 코드", LocalDateTime.now(), LocalDateTime.now());
		code.setCommonCodeGroup(codeGroup);
		cs.saveCode(code);
					
		log.info(codeGroup.toString());
		log.info(code.toString());
	}
	
	
	
}
