package com.jdc.clinic.dto.member;

import java.util.List;

import lombok.Data;

@Data
public class LineChartDataSet {
	String label;
	List<Long> data;

	String backgroundColor;
	String borderColor;
	int borderWidth;

}
