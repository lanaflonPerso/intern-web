package com.jdc.clinic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class ClinicDoctorPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "clinic_id", nullable = false)
	private int clinicId;

	@Column(name = "doctor_id", nullable = false)
	private int doctorId;

}