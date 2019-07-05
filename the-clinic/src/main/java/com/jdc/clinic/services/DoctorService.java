package com.jdc.clinic.services;

import java.util.List;
import java.util.Optional;
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

	@Autowired
	private DoctorRepo dRepo;

	@Transactional
	public List<Doctor> getDoctorsByClinicId(int id) {
		return cdRepo.findByClinicId(id).stream().filter(cd -> cd.getClinic().getId() == id).map(cd -> cd.getDoctor())
				.collect(Collectors.toList());
	}

	public Doctor save(Doctor doctor) {
		return dRepo.save(doctor);
	}

	public Optional<Doctor> findById(int id) {
		return dRepo.findById(id);
	}

	public List<Doctor> findAll() {
		return dRepo.findAll();
	}

	public void delete(int id) {
		dRepo.delete(dRepo.findById(id).get());
	}
	
	public Doctor findLast() {
		return dRepo.findById(dRepo.findAll().stream().mapToInt(d -> d.getId()).max().getAsInt()).get();
	}
}
