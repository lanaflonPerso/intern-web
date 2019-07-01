package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Partner extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Partner() {
		clinics = new ArrayList<>();
	}

	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	private List<Clinic> clinics;

}