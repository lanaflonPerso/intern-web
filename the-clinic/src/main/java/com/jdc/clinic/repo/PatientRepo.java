package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);

	@Query(value = "select count(*) from clinic join patient on clinic.id = patient.clinic_id where clinic.owner_phone = :phone", nativeQuery = true)
	Long countByPartnerPhone(String phone);

	List<Patient> findByFamilyMember(FamilyMember fm);

}
