package com.like.hrm.appointment.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.dto.AppointmentCodeDTO;
import com.like.hrm.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AppointmentController {

	@Resource
	private AppointmentService appointmentService;	

	@RequestMapping(value={"/hrm/code"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getCode() {
		
		AppointmentCode code = appointmentService.getAppointmentCode("AA");
					
		return WebControllerUtil.getResponse(code, 
				1, 
				true, 
				String.format("%d 건 조회되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody AppointmentCodeDTO.CodeSave code, BindingResult result) {				
		
		if ( result.hasErrors()) {
			log.info(result.toString());
			throw new ControllerException(result.toString());
		} 
																	
		appointmentService.saveAppintmentCode(code.getCommonCode());						
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
}
