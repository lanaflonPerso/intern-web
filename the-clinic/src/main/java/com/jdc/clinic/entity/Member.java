package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "member")
	private Set<FamilyMember> family;
	
	public Member() {
		family = new LinkedHashSet<>();
	}

}