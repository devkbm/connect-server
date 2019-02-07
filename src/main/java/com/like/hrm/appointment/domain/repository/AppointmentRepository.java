package com.like.hrm.appointment.domain.repository;

import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.id.CommonCodeId;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentMemorandum;

@Repository
public interface AppointmentRepository {

	/*
	AppointmentMemorandum getAppointmentMemorandum();
	
	void saveAppointmentMemorandum();
	
	void deleteAppointmentMemorandum();
	*/
	
	AppointmentCode getAppointmentCode(String codeId);
	
	void saveAppintmentCode(AppointmentCode appointmentCode);
	
	void deleteAppintmentCode(AppointmentCode appointmentCode);
	
}
