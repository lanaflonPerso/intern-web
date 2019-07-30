package com.jdc.clinic.repo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface FamilyMemberRepo extends BaseRepository<FamilyMember, Long> {

	public List<FamilyMember> findByMemberPhone(String phone);

	@Query(value = "select count(*) from family_member where member_phone=:phone", nativeQuery = true)
	public Long countByMemberPhone(String phone);

	public FamilyMember save(String familyMember2);
	
}

