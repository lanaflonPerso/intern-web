package com.jdc.clinic.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "member")
	private Set<FamilyMember> family;

}