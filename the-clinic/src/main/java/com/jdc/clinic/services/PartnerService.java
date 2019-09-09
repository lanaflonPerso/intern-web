package com.jdc.clinic.services;

import java.time.LocalDate;

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
		return clinicRepo.countByOwnerPhoneAndSecurityDeleteFalse(phone);
	}

	public Partner getPartner(String phone) {
		return partnerRepo.findById(phone).map(p -> {
			p.getClinics().size();
			return p;
		}).orElse(new Partner());
	}

	public Long confrimedBookingsCount(String phone) {
		return bookingRepo.countBookingsByPartnerPhoneToday(phone, LocalDate.now());
	}

	public Long ApplyBookingsCount(String phone) {
		return bookingRepo.countBookingsByPartnerPhone(phone, LocalDate.now());

	}

	public Long getPatientCountByPartnerPhone(String phone) {
		return patientRepo.countByClinicOwnerPhone(phone);
	}

	public Long getDoctorCountByParterPhone(String phone) {
		return clinicDoctorRepo.countDistinctDoctorByClinicOwnerPhone(phone);

	}

}