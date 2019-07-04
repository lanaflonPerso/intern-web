package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.repo.BookingRepo;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.DoctorRepo;
import com.jdc.clinic.repo.PartnerRepo;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class PartnerService {

	@Autowired
	ClinicRepo clinicRepo;

	@Autowired
	PartnerRepo partnerRepo;

	@Autowired
	BookingRepo bookingRepo;

	@Autowired
	PatientRepo patientRepo;

	@Autowired
	ClinicDoctorRepo clinicDoctorRepo;

	@Autowired
	DoctorRepo doctorRepo;

	public Long countClinicByPartner(String phone) {
		return clinicRepo.countByOwnerPhone(phone);
	}

	public Partner getPartner(String phone) {
		return partnerRepo.findById(phone).map(p -> {
			p.getClinics().size();
			return p;
		}).orElse(new Partner());
	}

	public Long getBookingCountByClinicID(int clinicID) {
		return 0L;
	}

	public Long getPatientCountByClinicID(int clinicID) {
		return patientRepo.countByClinicId(clinicID);
	}

	public Long countDoctorWhereParterPhone(String phone) {
		return clinicDoctorRepo.countDoctorWhereParterPhone(phone);
	}

}