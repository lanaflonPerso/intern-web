package com.jdc.clinic.repo;

import java.util.List;

import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);

	Long countByClinicOwnerPhone(String phone);

	List<Patient> findByFamilyMember(FamilyMember fm);

}