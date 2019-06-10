package com.jdc.clinic.entity;

import java.io.Serializable;

public class Township implements Serializable {

	private static final long serialVersionUID = 1L;

	public Township() {
	}

	private int id;

	private String name;

	private Division division;

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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
	
	

}