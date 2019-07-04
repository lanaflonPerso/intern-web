package com.jdc.clinic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface BookingRepo extends BaseRepository<Booking, Long> {

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone", nativeQuery = true)
	List<Booking> findBookingByMemberPhone(String phone);

	@Query(value = "select count(*) from booking join clinic on booking.clinic_id = clinic.id where clinic.owner_phone = :phone", nativeQuery = true)
	Long countBookingByPartnerPhone(String phone);

}
