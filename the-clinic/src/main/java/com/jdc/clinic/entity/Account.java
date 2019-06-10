package com.jdc.clinic.entity;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Account() {
	}

	private String phone;

	private String password;

	private String name;

	private String email;

	private Role role;

	private SecurityInfo security;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SecurityInfo getSecurity() {
		return security;
	}

	public void setSecurity(SecurityInfo security) {
		this.security = security;
	}

	public enum Role {
		Admin, Member, Partner
	}

}