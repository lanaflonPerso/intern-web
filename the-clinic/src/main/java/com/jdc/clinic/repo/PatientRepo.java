package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);

	@Query(value = "select count(*) from clinic join patient on clinic.id = patient.clinic_id where clinic.owner_phone = :phone", nativeQuery = true)
	Long countByPartnerPhone(String phone);

	List<Patient> findByFamilyMember(FamilyMember fm);

	@Query(value = "select distinct new com.jdc.clinic.dto.member.PartnerPatientCount(p1.clinic.id, (select count(*) from Patient p2 where p2.clinic.id = p1.clinic.id)) from Patient p1 where p1.clinic.owner.phone = :phone")
	List<PartnerPatientCount> getPatientCountAndClinicIDByPhone(String phone);

}
