package com.jdc.clinic.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.FamilyMemberRepo;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class PatientService {

	@Autowired
	PatientRepo patientRepo;

	@Autowired
	ClinicRepo clinicRepo;

	@Autowired
	FamilyMemberRepo fmRepo;

	public List<String> getClinicNameListByDate(String phone, LocalDate date) {
		return getDTOList(phone, date).stream().map(ppc -> ppc.getName()).collect(Collectors.toList());
	}

	public List<Long> getBookingRateListByDate(String phone, LocalDate date) {
		return getDTOList(phone, date).stream().map(ppc -> ppc.getCount()).collect(Collectors.toList());
	}

	private List<PartnerPatientCount> getDTOList(String phone, LocalDate date) {
		Map<String, Object> param = new HashMap<>();
		param.put("phone", phone);
		param.put("date", date);

		return patientRepo.find(
				"select distinct new com.jdc.clinic.dto.member.PartnerPatientCount(c1.id, c1.name, (select count(distinct b2) from Booking b2 where b2.patient.clinic.id = c1.id and b2.bookingDate = :date)) from Clinic c1 where c1.owner.phone = :phone",
				param, PartnerPatientCount.class);

	}

	@Transactional
	public Patient getPatient(int clinicID, long fmID) {

		Patient p = patientRepo.findByClinicIdAndFamilyMemberId(clinicID, fmID);

		if (p == null) {
			Patient pTemp = new Patient();
			pTemp.setClinic(clinicRepo.findById(clinicID).orElseThrow(EntityNotFoundException::new));
			pTemp.setFamilyMember(fmRepo.findById(fmID).orElseThrow(EntityNotFoundException::new));
			return patientRepo.save(pTemp);
		}
		return p;
	}

}