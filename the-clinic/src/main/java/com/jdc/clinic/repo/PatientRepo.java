package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);

	Long countByClinicOwnerPhone(String phone);

	List<Patient> findByFamilyMember(FamilyMember fm);

	@Query(value = "select p from Patient p where p.clinic.id = :clinicID and p.familyMember.id = :familyMemberID")
	Patient findByClinicIdAndFamilyMemberId(int clinicID, long familyMemberID);

	List<Patient> findByClinicId(int clinicId);

}