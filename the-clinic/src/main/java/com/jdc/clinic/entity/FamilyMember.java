package com.jdc.clinic.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class FamilyMember implements Serializable {

	private static final long serialVersionUID = 1L;

	public FamilyMember() {
		alergicMedicnes = new LinkedHashSet<>();
		diseasesHistories = new LinkedHashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Please enter Patient Name.")
	private String name;

	@Column
	private String relationship;

	@Column
	private LocalDate dob;

	@Enumerated
	private BloodType bloodType;

	@ElementCollection
	private Set<String> alergicMedicnes;

	@Embedded
	private SecurityInfo security;

	@Enumerated
	private Gender gender;
	
	@ManyToOne
	private Member member;

	@OneToMany(mappedBy = "patient")
	private Set<DiseasesHistory> diseasesHistories;

	public enum Gender {
		Male,
		Female
	}

	public enum BloodType {
		A, B, O, AB
	}

}