package com.jdc.clinic.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
		alergicMedicnes = new ArrayList<>();
		diseasesHistories = new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Please enter Patient Name.")
	private String name;

	private String relationship;

	private LocalDate dob;

	private BloodType bloodType;

	@ElementCollection
	private List<String> alergicMedicnes;

	@Embedded
	private SecurityInfo security;

	private Gender gender;
	
	@ElementCollection
	private List<String> phNo;

	@ManyToOne
	private Member member;

	@OneToMany(mappedBy = "patient")
	private List<DiseasesHistory> diseasesHistories;

	public enum Gender {
		Male, Female
	}

	public enum BloodType {
		A, B, O, AB
	}

}