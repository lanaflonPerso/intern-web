package com.jdc.clinic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class PatientPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "patient_id", nullable = false)
	private long patientId;

	@Column(name = "clinic_id", nullable = false)
	private int clinicId;

}