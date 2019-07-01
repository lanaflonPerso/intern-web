package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.repo.BookingRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.PartnerRepo;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class PartnerService {

	@Autowired
	ClinicRepo cRepo;

	@Autowired
	PartnerRepo ptRepo;

	@Autowired
	BookingRepo bRepo;

	@Autowired
	PatientRepo pRepo;

	public Long countClinicByPartner(String phone) {
		return cRepo.countByOwnerPhone(phone);
	}

	public Partner getPartner(String phone) {
		return ptRepo.getOne(phone);
	}

	public Long getBookingCountByClinicID(int clinicID) {
		return 0L;
	}

	public Long getPatientCountByClinicID(int clinicID) {
		return pRepo.countByClinicId(clinicID);
	}

}