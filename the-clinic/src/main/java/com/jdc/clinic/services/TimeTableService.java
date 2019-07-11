package com.jdc.clinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.TimeTableRepo;

@Service
public class TimeTableService {

	@Autowired
	private TimeTableRepo timeTableRepo;

	public List<Timetable> findAll() {
		return timeTableRepo.findAll();
	}

	@Transactional
	public Timetable save(Timetable timeTable) {
		return timeTableRepo.save(timeTable);
	}

	public List<Timetable> findDoctorsTimetableByClinicId(int id) {
		return timeTableRepo.findByClinicDoctorClinicId(id);
	}
}
