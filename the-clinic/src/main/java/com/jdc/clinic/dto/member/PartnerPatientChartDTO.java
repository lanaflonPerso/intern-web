package com.jdc.clinic.dto.member;

import lombok.Data;

@Data
public class PartnerPatientChartDTO {

	String label;
	int[] data;
	String backgroundColor;
	String borderColor;
	int borderWidth;
	boolean fill;
}
