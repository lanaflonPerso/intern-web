package com.jdc.clinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.repo.BookingRepo;
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

	public List<FamilyMember> getFamilyMembersByPhone(String phone) {
		return fmRepo.findByMemberPhone(phone);
	}

	public List<Clinic> findClinics() {
		return repo.findAll();
	}

	public List<Doctor> findDoctors() {
		return docRepo.findAll();
	}
}
