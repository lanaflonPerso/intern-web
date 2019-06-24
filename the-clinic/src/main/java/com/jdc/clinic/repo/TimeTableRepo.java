package com.jdc.clinic.repo;

import java.util.List;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface TimeTableRepo extends BaseRepository<Timetable, Long>{

	List<Timetable> findByClinicDoctorClinicId(int id);

}
