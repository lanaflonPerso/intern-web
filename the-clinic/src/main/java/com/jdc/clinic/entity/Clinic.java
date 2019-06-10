package com.jdc.clinic.entity;

import java.io.Serializable;

public class Clinic implements Serializable {

	private static final long serialVersionUID = 1L;

	public Clinic() {
	}

	private int id;

	private String name;

	private Partner owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Partner getOwner() {
		return owner;
	}

	public void setOwner(Partner owner) {
		this.owner = owner;
	}

}