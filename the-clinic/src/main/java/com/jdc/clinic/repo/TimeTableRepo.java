package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface TimeTableRepo extends BaseRepository<Timetable, Long> {

	List<Timetable> findByClinicDoctorClinicId(int id);

	@Query(value = "update Timetable t set t.security.delete = true")
	void deleteTimeTable(long id);

}
