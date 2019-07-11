package com.jdc.clinic.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.ClinicDoctorPK;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private ClinicDoctorRepo cdRepo;

	@Autowired
	private ClinicServices cService;

	@Autowired
	private DoctorRepo dRepo;

	@Transactional
	public List<Doctor> getDoctorsByClinicId(int id) {
		return cdRepo.findByClinicId(id).stream().filter(cd -> cd.getClinic().getId() == id).map(cd -> cd.getDoctor())
				.collect(Collectors.toList());
	}

	public void save(Doctor doctor) {
		Clinic c = cService.findByName(doctor.getHospital());
		Doctor d = dRepo.save(doctor);
		cdRepo.save(new ClinicDoctor(null, c, d, new ClinicDoctorPK(c.getId(), d.getId())));
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

	public List<Timetable> findSchedulesByDoctor(int id) {
		List<Timetable> timetablelist = new ArrayList<Timetable>();
		Map<Doctor, List<Timetable>> scheduleList = cService.findSchedules(id);
		Collection<List<Timetable>> timetable = scheduleList.values();
		for (List<Timetable> list : timetable) {
			if (list.iterator().hasNext()) {
				timetablelist.addAll(list);
			}

		}
		return timetablelist;
	}

}
