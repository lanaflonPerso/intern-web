package com.jdc.clinic.entity;

import java.io.Serializable;
import java.security.Security;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embedded
	private Security security;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id", insertable = false, updatable = false),
		@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
	})
	private ClinicDoctor clinicDoctor;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id", insertable = false, updatable = false),
		@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
	})
	private Patient patient;

	@ManyToOne
	private Timetable timeTable;

	private Status status;
	
	@EmbeddedId
	private BookingPK id;

	public enum Status {
		Apply,
		Confirmed,
		Cancel
	}

}