package com.jdc.clinic.repo;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface BookingRepo extends BaseRepository<Booking, Long> {

//	@Query("select b from Booking b join Patient p on b.patient.id = p.id join FamilyMember f on p.family_member_id = f.id join Member m on m.phone = f.member_phone where m.phone = :mPhone")
//	List<Booking> findBookingByMemberPhone(String mPhone);

}
