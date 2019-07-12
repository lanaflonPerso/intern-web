package com.jdc.clinic.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.repo.BookingRepo;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.DoctorRepo;
import com.jdc.clinic.repo.FamilyMemberRepo;

@Service
public class BookingService {

	@Autowired
	BookingRepo bRepo;

	@Autowired
	FamilyMemberRepo fmRepo;

	@Autowired
	ClinicRepo repo;

	@Autowired
	DoctorRepo docRepo;

	@Autowired
	ClinicDoctorRepo clinicDoctorRepo;

	public List<FamilyMember> getFamilyMembersByPhone(String phone) {
		return fmRepo.findByMemberPhone(phone);
	}

	public List<Clinic> findClinics() {
		return repo.findAll();
	}

	public List<Doctor> findDoctors() {
		return docRepo.findAll();
	}

	public List<Booking> listAllBookings() {
		return bRepo.findAll();
	}

	public List<Booking> getBookingsByMember(String memberPhone) {
		return bRepo.findBookingByMemberPhone(memberPhone);

	}

	public List<Booking> getBookingByMemberAndDate(String memberPhone) {
		return bRepo.findBookingByMemberPhoneAndDate(memberPhone);
	}

	public List<Booking> getBookingByDate(String memberPhone, LocalDate date) {
		return bRepo.findbookingByDate(memberPhone, date);
	}

	public List<Booking> getBookingByTodayDate(String partnerPhone) {
		return bRepo.findBookingByPartnerPhone(partnerPhone).stream()
				.filter(a -> ((a.getBookingDate()).compareTo(LocalDate.now())) == 0).collect(Collectors.toList());
	}

}
