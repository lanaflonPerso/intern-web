package com.jdc.clinic.dto.member;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChartDTO {
	private List<String> labels;

	private List<LineChartDataSet> dataSetList;

	public ChartDTO() {
		labels = new ArrayList<>();
		dataSetList = new ArrayList<>();
	}

	public void addLabel(String month) {
		labels.add(month);
	}
}
