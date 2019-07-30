package com.jdc.clinic.services;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.dto.member.DoctorTimetableDTO;
import com.jdc.clinic.dto.member.TimetableDTO;
import com.jdc.clinic.entity.Doctor;
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

	public List<DayOfWeek> findDayOfWeekByClinicId(int id) {
		return timeTableRepo.findDayOfWeekByClinicId(id);
	}

	public List<Timetable> findDoctorsTimetableByClinicIdAndDay(int id, DayOfWeek day) {
		Map<String, Object> params = new HashMap<>();
		params.put("clinicID", id);
		params.put("day", day);

		return timeTableRepo
				.find("select t from Timetable t where t.clinicDoctor.clinic.id = :clinicID and t.day = :day ", params);
	}

	@Transactional
	public List<TimetableDTO> findTimeTableDTO(int clinicID) {

		List<TimetableDTO> list = new ArrayList<TimetableDTO>();

		List<DayOfWeek> daysList = timeTableRepo.findDayOfWeekByClinicId(clinicID);

		daysList.forEach(day -> {
			Map<String, Object> params = new HashMap<>();
			params.put("clinicID", clinicID);
			params.put("day", day);
			TimetableDTO dto = new TimetableDTO();
			dto.setDay(day);
			dto.setTimeTable(timeTableRepo.find(
					"select t from Timetable t where t.clinicDoctor.clinic.id = :clinicID and t.day = :day order by t.timeFrom",
					params));
			list.add(dto);
		});
		return list;
	}

	@Transactional
	public List<Object> getDoctorTimeTable(int clinicID) {

		List<Object> dtoList = new ArrayList<>();

		Map<String, Object> params = new HashMap<>();
		params.put("clinicID", clinicID);

		String jpql1 = "select distinct t.clinicDoctor.doctor from Timetable t where t.clinicDoctor.clinic.id = :clinicID";

		List<Doctor> dList = timeTableRepo.find(jpql1, params, Doctor.class);

		dList.forEach(doctor -> {

			Map<String, Object> params2 = new HashMap<>();
			params2.put("clinicID", clinicID);
			params2.put("dID", doctor.getId());
			String jpql2 = "select t.day from Timetable t where t.clinicDoctor.clinic.id = :clinicID and t.clinicDoctor.doctor.id = :dID";

			String str = "";
			List<DayOfWeek> ddList = timeTableRepo.find(jpql2, params2, DayOfWeek.class);

			for (DayOfWeek d : ddList) {
				str += d.getDisplayName(TextStyle.SHORT, Locale.getDefault()) + ", ";
			}

			dtoList.add(new DoctorTimetableDTO(doctor, str.substring(0, str.length() - 2)));
		});

		return dtoList;
	}

}
