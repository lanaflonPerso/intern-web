package com.jdc.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private ClinicDoctorRepo cdRepo;
//
//	@Autowired
//	private ClinicServices cService;

	@Autowired
	private DoctorRepo dRepo;

	@Transactional
	public List<Doctor> getDoctorsByClinicId(int id) {
		return cdRepo.findByClinicId(id).stream().filter(cd -> cd.getClinic().getId() == id).map(cd -> cd.getDoctor())
				.collect(Collectors.toList());
	}

	public Doctor save(Doctor doctor) {
		doctor.setDegrees(doctor.getDegrees().stream().filter(d -> !d.isEmpty()).collect(Collectors.toList()));
		doctor.setPhones(doctor.getPhones().stream().filter(p -> !p.isEmpty()).collect(Collectors.toList()));
		doctor.setSpecialities(
				doctor.getSpecialities().stream().filter(s -> !s.isEmpty()).collect(Collectors.toList()));
		return dRepo.save(doctor);
	}

	public Doctor findById(int id) {
		return dRepo.getOne(id);
	}

	public List<Doctor> findAll() {
		return dRepo.findAll();
	}

	public void delete(Doctor d) {
		d.getSecurity().setDelete(true);
		dRepo.save(d);
	}

	public Doctor findLast() {
		return dRepo.findById(dRepo.findAll().stream().mapToInt(d -> d.getId()).max().getAsInt()).get();
	}

	public List<String> getSpecialitiesList() {
		return dRepo.findSpecialities();
	}

	public Doctor findByLicenseCode(String licenseCode) {
		return dRepo.findByLicenseCode(licenseCode);
	}
}
