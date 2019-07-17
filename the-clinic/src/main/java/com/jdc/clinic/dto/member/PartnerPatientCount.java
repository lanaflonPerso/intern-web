package com.jdc.clinic.dto.member;

import lombok.Data;

@Data
public class PartnerPatientCount {
	int id;
	long count;

	public PartnerPatientCount(int id, long count) {
		this.id = id;
		this.count = count;
	}

}
