package com.jdc.clinic.dto.member;

import lombok.Data;

@Data
public class PartnerPatientCount {
	long id;
	String name;
	long count;

	public PartnerPatientCount(int id, String name, long count) {
		this.id = id;
		this.name = name;
		this.count = count;
	}

	public PartnerPatientCount(long id, String name, long count) {
		this.id = id;
		this.name = name;
		this.count = count;
	}

}
