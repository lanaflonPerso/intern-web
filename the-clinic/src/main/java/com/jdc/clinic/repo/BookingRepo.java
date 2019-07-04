package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface BookingRepo extends BaseRepository<Booking, Long> {

//	@Query("select b from Booking b join Patient p on b.patient.id = p.id join FamilyMember f on p.family_member_id = f.id join Member m on m.phone = f.member_phone where m.phone = :mPhone")
//	List<Booking> findBookingByMemberPhone(String mPhone);

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone", nativeQuery = true)
	List<Booking> findBookingByMemberPhone(String phone);

}
