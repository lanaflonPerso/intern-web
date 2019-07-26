package com.jdc.clinic.repo;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface TimeTableRepo extends BaseRepository<Timetable, Long> {

	List<Timetable> findByClinicDoctorClinicId(int id);

	List<Timetable> findByClinicDoctorClinicIdAndDay(int id, DayOfWeek day);

	@Query(value = "update Timetable t set t.security.delete = true")
	void deleteTimeTable(long id);

}
