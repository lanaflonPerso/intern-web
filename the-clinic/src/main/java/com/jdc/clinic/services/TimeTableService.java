package com.jdc.clinic.services;

import java.time.DayOfWeek;
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

	public Timetable findById(long id) {
		return timeTableRepo.getOne(id);
	}

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

	public List<Timetable> findDoctorsTimetableByClinicIdAndDay(int id, DayOfWeek day) {
		return timeTableRepo.findByClinicDoctorClinicIdAndDay(id, day);
	}
}
