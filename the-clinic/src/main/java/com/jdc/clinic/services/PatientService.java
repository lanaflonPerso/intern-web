package com.jdc.clinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class PatientService {

	@Autowired
	PatientRepo patientRepo;

	public List<PartnerPatientCount> getPieChartData(String phone) {

		return patientRepo.getPatientCountAndClinicIDByPhone(phone);

	}
}
