package com.like.hrm.employee.domain.service;

import java.util.List;

import javax.annotation.Resource;

import com.like.hrm.appointment.domain.entity.AppointmentDetails;
import com.like.hrm.appointment.domain.entity.AppointmentMemorandum;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;

public class AppointmentService {

	@Resource
	private AppointmentRepository appointmentRepository;
		
	public void Appoinment(String AppointmentId) {
		AppointmentMemorandum memorandum = appointmentRepository.getAppointmentMemorandum();
				
		List<AppointmentDetails> appointmentList = memorandum.getDetails();
		
		for (AppointmentDetails appointment : appointmentList) {
			
		}
			
	}
	
	
}
