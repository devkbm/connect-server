package com.like.hrm.employee.domain.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentDetails;
import com.like.hrm.appointment.domain.model.AppointmentMemorandum;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentProcessService implements ApplicationListener<ProcessEvent> {

	private AppointmentRepository appointmentRepository;
		
	public void Appoinment(String AppointmentId) {
		/*
		AppointmentMemorandum memorandum = appointmentRepository.getAppointmentMemorandum();
				
		List<AppointmentDetails> appointmentList = memorandum.getDetails();
		
		for (AppointmentDetails appointment : appointmentList) {
			
		}
		*/
			
	}

	@Override
	public void onApplicationEvent(ProcessEvent event) {
		log.info("이벤트 구독");
		System.out.println(event.toString());
		
	}
	
	
}
