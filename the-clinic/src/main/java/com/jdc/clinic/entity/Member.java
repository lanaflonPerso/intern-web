package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

public class Member extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Member() {
	}

	private Set<FamilyMember> family;

	public Set<FamilyMember> getFamily() {
		return family;
	}

	public void setFamily(Set<FamilyMember> family) {
		this.family = family;
	}

}