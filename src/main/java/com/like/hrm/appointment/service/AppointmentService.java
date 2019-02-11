package com.like.hrm.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetails;
import com.like.hrm.appointment.domain.model.AppointmentLedger;
import com.like.hrm.appointment.infra.jparepository.AppointmentJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AppointmentService {

	@Autowired
	public ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private AppointmentJpaRepository appointmentJpaRepository;
	
	public void doSomething() {
		log.info("서비스 발행");
		applicationEventPublisher.publishEvent(new ProcessEvent(this, new AppointmentLedger()));
	}
	
	public AppointmentCode getAppointmentCode(String codeId) {
		return appointmentJpaRepository.getAppointmentCode(codeId);
	}
	
	public void saveAppintmentCode(AppointmentCode appointmentCode) {
		appointmentJpaRepository.saveAppintmentCode(appointmentCode);
	}
	
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		appointmentJpaRepository.deleteAppintmentCode(appointmentCode);
	}
	
	public AppointmentCodeDetails getAppointmentCodeDetails(Long id) {				
		return appointmentJpaRepository.getAppointmentCodeDetails(id);
	}
	
	public void saveAppointmentCodeDetails(AppointmentCodeDetails details) {
		appointmentJpaRepository.saveAppointmentCodeDetails(details);
	}
	
	public void deleteAppointmentCodeDetails(AppointmentCodeDetails details) {
		appointmentJpaRepository.deleteAppointmentCodeDetails(details);
	}


}
