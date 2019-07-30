package com.jdc.clinic.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String phone;

	@NotEmpty(message = "Please enter password.")
	private String password;

	@NotEmpty(message = "Please enter Name.")
	private String name;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Embedded
	private SecurityInfo security;

	public enum Role {
		ROLE_Admin, ROLE_Member, ROLE_Partner
	}

	public Account() {
		security = new SecurityInfo();
	}

}