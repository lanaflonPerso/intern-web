package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

public class Partner extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Partner() {
	}

	private Set<Clinic> clinics;

	public Set<Clinic> getClinics() {
		return clinics;
	}

	public void setClinics(Set<Clinic> clinics) {
		this.clinics = clinics;
	}

}