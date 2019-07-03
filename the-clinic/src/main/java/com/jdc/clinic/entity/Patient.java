package com.jdc.clinic.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embedded
	private SecurityInfo security;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private FamilyMember familyMember;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Clinic clinic;

}