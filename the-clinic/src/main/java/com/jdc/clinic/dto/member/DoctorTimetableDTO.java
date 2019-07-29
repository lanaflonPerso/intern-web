package com.jdc.clinic.dto.member;

import com.jdc.clinic.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorTimetableDTO {
	Doctor doctor;
	String days;

}
