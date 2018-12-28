package com.like.hrm.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.employee.domain.service.AppointmentProcessService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentService {

	@Autowired
	public ApplicationEventPublisher applicationEventPublisher;
	
	public void doSomething() {
		log.info("서비스 발행");
		applicationEventPublisher.publishEvent(new ProcessEvent(this));
	}


}
