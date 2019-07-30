package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Member;
import com.jdc.clinic.repo.BookingRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.FamilyMemberRepo;
import com.jdc.clinic.repo.MemberRepo;

@Service
public class MemberService {

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private BookingRepo bRepo;

	@Autowired
	private FamilyMemberRepo fmRepo;

	@Autowired
	private ClinicRepo cRepo;

	/*
	 * @Autowired public MemberEventRepo meRepo;
	 */

	public Member saveMember(Member memeber) {
		return memberRepo.save(memeber);
	}

	public Member getMemberByPhone(String phone) {
		return memberRepo.getOne(phone);
	}

	public Long getBookingCountByMemberPhone(String phone) {
		return bRepo.countBookingByMemberPhone(phone);
	}

	public Long getMemberCountByMemberPhone(String phone) {
		return fmRepo.countByMemberPhone(phone);
	}

	public Long getClinicCountByMemberPhone(String phone) {
		return cRepo.countByMemberPhone(phone);
	}

	/*
	 * public Long getEventCountByMemberPhone(String phone) { return
	 * meRepo.countByMemberPhone(phone); }
	 */

}
