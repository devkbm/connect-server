package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.commoncode.domain.model.AbstractCode;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@DiscriminatorValue("H0001")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCode extends AbstractCode implements Serializable {
	 
	private static final long serialVersionUID = -2792716645396219283L;

	@OneToMany(mappedBy = "appointmentCode")
	private List<AppointmentCodeDetails> codeDetails = new ArrayList<>();		
	
	@Builder
	public AppointmentCode(String code, String codeName, LocalDateTime fromDate, LocalDateTime toDate) {
		this.code 		= code;
		this.codeName 	= codeName;
		this.fromDate 	= fromDate;
		this.toDate 	= toDate;
	}
}
