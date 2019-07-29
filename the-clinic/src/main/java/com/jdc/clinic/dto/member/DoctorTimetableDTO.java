package com.jdc.clinic.dto.member;

import java.time.DayOfWeek;
import java.util.List;

import com.jdc.clinic.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorTimetableDTO {
	Doctor doctor;
	List<DayOfWeek> days;
}
