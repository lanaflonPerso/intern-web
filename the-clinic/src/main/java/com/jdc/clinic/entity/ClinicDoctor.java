package com.jdc.clinic.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ClinicDoctor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embedded
	private SecurityInfo security;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Clinic clinic;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Doctor doctor;
	
	@EmbeddedId
	private ClinicDoctorPK id;

}