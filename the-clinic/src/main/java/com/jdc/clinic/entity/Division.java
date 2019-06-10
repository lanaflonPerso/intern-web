package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

public class Division implements Serializable {

	private static final long serialVersionUID = 1L;

	public Division() {
	}

	private int id;

	private String name;

	private Set<Township> townships;

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

	public Set<Township> getTownships() {
		return townships;
	}

	public void setTownships(Set<Township> townships) {
		this.townships = townships;
	}

}