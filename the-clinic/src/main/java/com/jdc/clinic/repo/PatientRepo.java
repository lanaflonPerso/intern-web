package com.jdc.clinic.repo;

import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);
}
