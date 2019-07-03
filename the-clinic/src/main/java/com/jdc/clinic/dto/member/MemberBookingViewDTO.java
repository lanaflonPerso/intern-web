package com.jdc.clinic.dto.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.Patient;

import lombok.Data;

@Data
public class MemberBookingViewDTO {

	List<Patient> patientList;
	List<Doctor> doctorList;
	List<Clinic> clinicList;
	LocalDate date;

	public MemberBookingViewDTO() {
		patientList = new ArrayList<Patient>();
		doctorList = new ArrayList<Doctor>();
		clinicList = new ArrayList<Clinic>();
	}
}
