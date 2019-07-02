package com.jdc.clinic.repo;

import java.util.List;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface ClinicRepo extends BaseRepository<Clinic, Integer> {

	Long countByOwnerPhone(String phone);

	List<Clinic> findClinicById(int id);

	List<Clinic> findByOwnerPhone(String phone);

}