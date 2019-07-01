package com.jdc.clinic.repo;

import java.util.List;

import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface FamilyMemberRepo extends BaseRepository<FamilyMember, Long> {

	public List<FamilyMember> findByMemberPhone(String phone);

}
