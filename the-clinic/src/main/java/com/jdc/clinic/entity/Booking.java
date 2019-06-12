package com.jdc.clinic.entity;

import java.io.Serializable;
import java.security.Security;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Embedded
	private Security security;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "clinic_id", referencedColumnName = "clinic_id"),
			@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id") })
	private ClinicDoctor clinicDoctor;

	@ManyToOne
	private Patient patient;

	private LocalDate bookingDate;

	@ManyToOne
	private Timetable timeTable;

	private Status status;

	public enum Status {
		Apply, Confirmed, Cancel
	}

}