package com.jdc.clinic.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.FamilyMember;
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

	public Member saveMember(Member member) {
		return memberRepo.save(member);
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
	public List<FamilyMember> findAll(String phone) {
		System.out.println(phone);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		return fmRepo.find(
				"select fm from FamilyMember fm where fm.security.delete = false and fm.member.phone = :phone", params);
	}

	public Optional<FamilyMember> findMemberById(long id) {
		return fmRepo.findById(id);
	}

	public FamilyMember findById(long id) {
		return fmRepo.getOne(id);
	}

	public FamilyMember save(FamilyMember familyMember) {
		return fmRepo.save(familyMember);
	}

	public FamilyMember delete(FamilyMember familyMember) {
		fmRepo.getOne(familyMember.getId()).getSecurity().setDelete(true);
		return fmRepo.save(familyMember);
	}

	public FamilyMember edit(FamilyMember familyMember) {
		return fmRepo.save(familyMember);
	}

}
