package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.ClinicDoctorPK;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface ClinicDoctorRepo extends BaseRepository<ClinicDoctor, ClinicDoctorPK> {

	List<ClinicDoctor> findByClinicId(int clinicID);

	Long countDoctorByClinicId(int clinicID);
	
	@Query(value = "select count(*) from clinic join clinic_doctor on clinic.id = clinic_doctor.clinic_id where owner_phone = :phone", nativeQuery = true)
	Long countDoctorWhereParterPhone(String phone);
}
