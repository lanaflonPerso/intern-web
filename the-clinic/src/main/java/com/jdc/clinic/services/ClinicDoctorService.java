package com.jdc.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.ClinicDoctorPK;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.DoctorRepo;

@Service
public class ClinicDoctorService {

	@Autowired
	private ClinicDoctorRepo cliRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private DoctorRepo doctorRepo;

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

	public Long countDoctorByClinicId(int clinicId) {
		return cliRepo.countDoctorByClinicId(clinicId);
	}

	@Transactional
	public ClinicDoctor saveClinicDoctor(int clinicID, int doctorID) {
		ClinicDoctor clincDoctor = new ClinicDoctor();
		clincDoctor.setClinic(clinicRepo.getOne(clinicID));
		clincDoctor.setDoctor(doctorRepo.getOne(doctorID));
		clincDoctor.setId(new ClinicDoctorPK(clinicID, doctorID));
		return cliRepo.save(clincDoctor);

	}
}
