package com.jdc.clinic.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class FamilyMember implements Serializable {

	private static final long serialVersionUID = 1L;

	public FamilyMember() {
	}

	private long id;

	private String name;

	private String relationship;

	private LocalDate dob;

	private Gender gender;

	private Member member;

	public enum Gender {
		Male, Female
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}