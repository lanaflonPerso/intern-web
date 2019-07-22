package com.jdc.clinic.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface PatientRepo extends BaseRepository<Patient, Long> {

	Long countByClinicId(int clinicID);

	Long countByClinicOwnerPhone(String phone);

	List<Patient> findByFamilyMember(FamilyMember fm);

	@Query(value = "select distinct new com.jdc.clinic.dto.member.PartnerPatientCount(c1.id, c1.name, (select count(distinct b2) from Booking b2 where b2.patient.clinic.id = c1.id and b2.bookingDate = :date)) from Clinic c1 where c1.owner.phone = :phone")
	List<PartnerPatientCount> getPatientCountAndClinicIDByPhone(@Param("phone") String phone, LocalDate date);

}