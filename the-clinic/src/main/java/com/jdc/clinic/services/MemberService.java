package com.jdc.clinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.entity.Member;
import com.jdc.clinic.repo.MemberRepo;

@Service
public class MemberService {

	@Autowired
	private MemberRepo memberRepo;

	public Member getMemberByPhone(String phone) {
		return memberRepo.getOne(phone);
	}

}
