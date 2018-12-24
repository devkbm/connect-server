package com.like.hrm.appointment.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AppointmentMemorandum {

	String AppointmentId;
	
	LocalDate registrationDate;
		
	List<AppointmentDetails> details = new ArrayList<>();
			
	public void process() {
		for (AppointmentDetails detail: this.details) {
			
		}
		
	}
}
