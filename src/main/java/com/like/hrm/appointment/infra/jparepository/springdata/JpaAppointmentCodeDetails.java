package com.like.hrm.appointment.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentCodeDetails;

@Repository
public interface JpaAppointmentCodeDetails extends JpaRepository<AppointmentCodeDetails, Long> {

}
