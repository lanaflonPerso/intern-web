package com.jdc.clinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Division;
import com.jdc.clinic.entity.Township;
import com.jdc.clinic.repo.DivisionRepo;
import com.jdc.clinic.repo.TownshipRepo;

@Service
public class LocationService {

	@Autowired
	private TownshipRepo townshipRepo;
	@Autowired
	private DivisionRepo divisionRepo;

	public List<Division> findValid() {
		return divisionRepo.findAll();
	}

	public List<Township> findTownships(int divisionId) {
		return townshipRepo.findByDivisionId(divisionId);
	}

	public Township findTownshipById(int townshipID) {
		return townshipRepo.getOne(townshipID);
	}
}
