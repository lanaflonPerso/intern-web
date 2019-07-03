package com.jdc.clinic.repo;

import java.util.List;



import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.ClinicDoctorPK;

import com.jdc.clinic.repo.custom.BaseRepository;

public interface ClinicDoctorRepo extends BaseRepository<ClinicDoctor, ClinicDoctorPK> {

	List<ClinicDoctor> findByClinicId(int clinicID);

	Long countDoctorByClinicId(int clinicID);
}
