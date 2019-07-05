package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Clinic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Please enter Clinic Name.")
	private String name;

	@ElementCollection
	private List<String> phone;

	@ElementCollection
	private List<String> mails;

	@Embedded
	private SecurityInfo security;

	@ManyToOne
	private Partner owner;

	@OneToOne(mappedBy = "clinic", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private Address addrress;

	@OneToMany(mappedBy = "clinic", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private List<OpenTime> openTime;

	public Clinic() {
		phone = new ArrayList<String>();
		mails = new ArrayList<String>();
		addrress = new Address();
		security = new SecurityInfo();
		openTime = new ArrayList<OpenTime>();
	}

	@PrePersist
	private void prePersist() {
		addrress.setClinic(this);
		openTime.forEach(o -> o.setClinic(this));
	}

}