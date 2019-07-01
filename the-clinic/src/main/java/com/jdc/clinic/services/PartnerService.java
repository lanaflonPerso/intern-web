package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.PartnerRepo;

@Service
public class PartnerService {

	@Autowired
	ClinicRepo cRepo;

	@Autowired
	PartnerRepo pRepo;

	public Long countClinicByPartner(String phone) {
		return cRepo.countByOwnerPhone(phone);
	}

	public Partner getPartner(String phone) {
		return pRepo.getOne(phone);
	}

}