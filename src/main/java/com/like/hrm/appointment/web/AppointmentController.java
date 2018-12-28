package com.like.hrm.appointment.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.commoncode.web.dto.CodeGroupQueryDTO;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	@Resource
	private AppointmentService appointmentService;
	
	@RequestMapping(value={"/hrm/test"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCodeGroups(@ModelAttribute CodeGroupQueryDTO commonCodeGroupQueryDTO) {
		
		appointmentService.doSomething(); 							
		
		return WebControllerUtil.getResponse(null, 
				0, 
				true, 
				String.format("%d 건 조회되었습니다.", 0), 
				HttpStatus.OK);
	}
}
