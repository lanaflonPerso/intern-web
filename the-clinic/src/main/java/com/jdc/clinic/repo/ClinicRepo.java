package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface ClinicRepo extends BaseRepository<Clinic, Integer> {

	Long countByOwnerPhone(String phone);

	List<Clinic> findClinicById(int id);

	List<Clinic> findByOwnerPhone(String phone);

	List<Clinic> findByNameContainingIgnoreCase(String name);

	@Query(value = "select count(distinct clinic_id) from clinic join patient on clinic.id = patient.id join family_member on patient.id=family_member.id where member_phone =:phone", nativeQuery = true)
	public Long countByMemberPhone(String phone);
}