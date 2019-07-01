package com.jdc.clinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.ClinicDoctorRepo;

@Service
public class DoctorService {

	@Autowired
	ClinicDoctorRepo cdRepo;

	public List<Doctor> getDoctorsByClinicId(int clinicID) {
		return cdRepo.findDoctorByClinicId(clinicID);
	}
}
