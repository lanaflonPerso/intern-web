package com.jdc.clinic.services;

import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.OpenTime;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.OpenTimeRepo;

@Service
public class OpenTimeService {

	@Autowired
	OpenTimeRepo openTimeRepo;

	@Autowired
	ClinicRepo clinicRepo;

	public OpenTime set24hrByClinicId(int id, DayOfWeek day) {

		OpenTime openTime = new OpenTime();
		openTime.set24hr(true);
		openTime.setDayOfWeek(day);
		openTime.setClinic(clinicRepo.getOne(id));

		return openTimeRepo.save(openTime);
	}
}
