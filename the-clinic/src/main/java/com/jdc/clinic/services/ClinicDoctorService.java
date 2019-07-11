package com.jdc.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.ClinicDoctorRepo;

@Service
public class ClinicDoctorService {

	@Autowired
	private ClinicDoctorRepo cliRepo;

	public ClinicDoctor save(ClinicDoctor cDoctor) {
		return cliRepo.save(cDoctor);
	}

	@Transactional
	public List<Doctor> getDoctorsByClinicId(int id) {
		return cliRepo.findByClinicId(id).stream().filter(cd -> cd.getClinic().getId() == id).map(cd -> cd.getDoctor())
				.collect(Collectors.toList());
	}

	public List<ClinicDoctor> findAll() {
		return cliRepo.findAll();
	}
}
