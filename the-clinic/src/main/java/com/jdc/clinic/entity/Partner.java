package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partner extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Partner() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Set<Clinic> clinics;

	public Set<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(Set<Clinic> clinics) {
		this.clinics = clinics;
	}

}