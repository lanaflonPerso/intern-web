package com.jdc.clinic.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class PatientService {

	@Autowired
	PatientRepo patientRepo;

	public List<PartnerPatientCount> getPieChartDataByDate(String phone, LocalDate date) {
		return patientRepo.getPatientCountAndClinicIDByPhone(phone, date);

	}
}
