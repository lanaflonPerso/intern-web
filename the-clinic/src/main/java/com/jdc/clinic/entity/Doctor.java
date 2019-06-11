package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.jdc.clinic.entity.FamilyMember.Gender;

import lombok.Data;

@Entity
@Data
public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Please enter Doctor Name.")
	private String name;

	@ElementCollection
	private Set<String> degrees;

	@ElementCollection
	private Set<String> specialities;
	
	private Gender gender;

	private String hospital;

	private String position;

	@Embedded
	private SecurityInfo security;
	
	public Doctor() {
		degrees = new LinkedHashSet<>();
		specialities = new LinkedHashSet<>();
	}

}