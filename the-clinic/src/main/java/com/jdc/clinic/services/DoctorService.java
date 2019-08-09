package com.jdc.clinic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.DoctorRepo;
import com.jdc.clinic.repo.TimeTableRepo;

@Service
public class DoctorService {

	@Autowired
	private ClinicDoctorRepo cdRepo;

	@Autowired
	private TimeTableRepo timeTableRepo;

	@Autowired
	private DoctorRepo dRepo;

	@Transactional
	public List<Doctor> getDoctorsByClinicId(int id) {
		return cdRepo.findByClinicId(id).stream().filter(cd -> cd.getClinic().getId() == id).map(cd -> cd.getDoctor())
				.collect(Collectors.toList());
	}

	public void save(Doctor doctor) {
		/* Clinic c = cService.findByName(doctor.getHospital()); */
		dRepo.save(doctor);
		/*
		 * cdRepo.save(new ClinicDoctor(null, c, d, new ClinicDoctorPK(c.getId(),
		 * d.getId())));
		 */
	}

	public Doctor findById(int id) {
		return dRepo.getOne(id);
	}

	public List<Doctor> findAll() {
		return dRepo.findAll();
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

	public void delete(int doctorID) {
		// Doctor Delete
		dRepo.getOne(doctorID).getSecurity().setDelete(true);

		// Clinic DoctorDelete
		cdRepo.findByDoctorId(doctorID).stream().map(cd -> {
			cd.getSecurity().setDelete(true);
			return cdRepo.save(cd);
		});

		// TimeTable Delete
		timeTableRepo.findByClinicDoctorDoctorId(doctorID).stream().map(timetable -> {
			timetable.getSecurity().setDelete(true);
			return timeTableRepo.save(timetable);
		});
	}

}
