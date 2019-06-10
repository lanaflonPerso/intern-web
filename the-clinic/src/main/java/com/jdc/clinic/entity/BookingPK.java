package com.jdc.clinic.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class BookingPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "clinic_id", nullable = false)
	private int clinicId;

	@Column(name = "doctor_id", nullable = false)
	private int doctorId;

	@Column(name = "patient_id", nullable = false)
	private long patientId;

	@Column(nullable = false)
	private LocalDate bookDate;

}