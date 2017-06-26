package com.like.code;

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

import com.like.code.domain.model.CommonCode;
import com.like.code.domain.model.CommonCodeGroup;
import com.like.code.domain.model.id.CommonCodeId;
import com.like.code.service.CommonCodeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommonCodeTest {

	private static final Logger log = LoggerFactory.getLogger(CommonCodeTest.class);
	
	@Autowired
	CommonCodeService cs;
			
	@Test
	public void 코드그룹추가() {
		CommonCodeGroup codeGroup = new CommonCodeGroup("A01","테스트 코드 그룹");
					
		cs.saveCodeGroup(codeGroup);
					
		log.info(codeGroup.toString());				
	}
	
	@Test
	public void 코드추가() {
		CommonCodeGroup codeGroup = new CommonCodeGroup("A01","테스트 코드 그룹");
		
		cs.saveCodeGroup(codeGroup);
						
		CommonCode code = new CommonCode(new CommonCodeId("A01", "AA"), "테스트 코드", LocalDateTime.now(), LocalDateTime.now());
		code.setCommonCodeGroup(codeGroup);
		cs.saveCode(code);
					
		log.info(codeGroup.toString());
		log.info(code.toString());
	}
	
	
	
}
