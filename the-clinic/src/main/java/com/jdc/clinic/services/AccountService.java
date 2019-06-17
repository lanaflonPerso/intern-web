package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.repo.AccountRepo;
import com.jdc.clinic.utils.ClinicException;

@Service
public class AccountService {

	@Autowired
	private AccountRepo repo;

	public Account findByLoginId(String loginId) {
		return repo.findById(loginId).orElseThrow(() -> new ClinicException("Invalid Phone Number."));
	}
}
