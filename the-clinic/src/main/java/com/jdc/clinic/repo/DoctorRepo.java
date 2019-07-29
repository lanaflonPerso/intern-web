package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface DoctorRepo extends BaseRepository<Doctor, Integer> {

	@Query(value = "select count(distinct doctor_id) from clinic join clinic_doctor on clinic.id = clinic_doctor.clinic_id where owner_phone = :phone", nativeQuery = true)
	Long countDoctorWhereParterPhone(String phone);

	@Query(value = "select distinct specialities from doctor_specialities", nativeQuery = true)
	List<String> findSpecialities();

	Doctor findByLicenseCode(String licenseCode);

}
