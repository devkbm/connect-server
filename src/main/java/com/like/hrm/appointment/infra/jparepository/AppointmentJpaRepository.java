package com.like.hrm.appointment.infra.jparepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetails;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCode;
import com.like.hrm.appointment.infra.jparepository.springdata.JpaAppointmentCodeDetails;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AppointmentJpaRepository implements AppointmentRepository {

	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaAppointmentCode jpaAppointmentCode;	
	
	@Autowired
	private JpaAppointmentCodeDetails jpaAppointmentCodeDetails;
	
	@Override
	public AppointmentCode getAppointmentCode(String codeId) {
		return jpaAppointmentCode.findOne(codeId);
	}

	@Override
	public void saveAppintmentCode(AppointmentCode appointmentCode) {
		jpaAppointmentCode.save(appointmentCode);		
	}

	@Override
	public void deleteAppintmentCode(AppointmentCode appointmentCode) {
		jpaAppointmentCode.delete(appointmentCode);		
	}

	@Override
	public AppointmentCodeDetails getAppointmentCodeDetails(Long id) {
		return jpaAppointmentCodeDetails.findOne(id);
	}

	@Override
	public void saveAppointmentCodeDetails(AppointmentCodeDetails details) {
		jpaAppointmentCodeDetails.save(details);
		
	}

	@Override
	public void deleteAppointmentCodeDetails(AppointmentCodeDetails appointmentCode) {
		jpaAppointmentCodeDetails.delete(appointmentCode);
		
	}

}
