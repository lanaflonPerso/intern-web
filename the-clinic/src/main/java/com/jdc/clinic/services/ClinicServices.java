package com.jdc.clinic.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.TimeTableRepo;

@Service
public class ClinicServices {

	@Autowired
	private ClinicRepo clinicRepo;
	@Autowired
	private TimeTableRepo timeTableRepo;

	public List<Clinic> search(String keyword) {
		return null;
	}

	@Transactional
	public Clinic findById(int id) {
		return clinicRepo.findById(id).map(c -> {
			c.getMails().size();
			c.getPhone().size();
			return c;
		}).orElse(new Clinic());
	}

	public Clinic save(Clinic clinic) {
		return clinicRepo.saveAndFlush(clinic);
	}

	public Map<Doctor, List<Timetable>> findSchedules(int id) {
		return timeTableRepo.findByClinicDoctorClinicId(id).stream()
				.collect(Collectors.groupingBy(a -> a.getClinicDoctor().getDoctor()));

	}
}
