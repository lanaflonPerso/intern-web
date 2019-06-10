package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Member extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Member() {
	}

	@OneToMany(mappedBy = "member")
	private Set<FamilyMember> family;

	public Set<FamilyMember> getFamily() {
		return family;
	}

	public void setFamily(Set<FamilyMember> family) {
		this.family = family;
	}

}