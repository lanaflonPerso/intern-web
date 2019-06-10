package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Partner extends Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Partner() {
		clinics = new LinkedHashSet<>();
	}

	@OneToMany(mappedBy = "owner")
	private Set<Clinic> clinics;

}