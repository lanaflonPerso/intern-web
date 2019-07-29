package com.jdc.clinic.dto.member;

import java.time.DayOfWeek;
import java.util.List;

import com.jdc.clinic.entity.Timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {

	DayOfWeek day;
	List<Timetable> timeTable;

}
