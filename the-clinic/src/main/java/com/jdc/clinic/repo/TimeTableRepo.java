package com.jdc.clinic.repo;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface TimeTableRepo extends BaseRepository<Timetable, Long> {

	List<Timetable> findByClinicDoctorClinicId(int id);

	@Query(value = "select distinct t.day from Timetable t where t.clinicDoctor.clinic.id = :id order by t.day")
	List<DayOfWeek> findDayOfWeekByClinicId(int id);

	List<Timetable> findByClinicDoctorClinicIdAndDay(int clinicID, DayOfWeek day);

	@Query(value = "update Timetable t set t.security.delete = true")
	void deleteTimeTable(long id);

}
