package com.jdc.clinic.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface BookingRepo extends BaseRepository<Booking, Long> {

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone", nativeQuery = true)
	List<Booking> findBookingByMemberPhone(String phone);

	@Query(value = "select count(b) from Booking b where b.patient.clinic.owner.phone = :phone and b.bookingDate = date(now())")
	Long countBookingsByPartnerPhone(String phone);

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone and date(booking.booking_date)>=date(now())", nativeQuery = true)
	List<Booking> findBookingByMemberPhoneAndDate(String phone);

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone and date(booking.booking_date)=:date", nativeQuery = true)
	List<Booking> findbookingByDate(String phone, LocalDate date);

	@Query(value = "select count(*) from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone=:phone", nativeQuery = true)
	Long countBookingByMemberPhone(String phone);

	@Query(value = "select booking.* from booking join clinic on booking.clinic_id = clinic.id where clinic.owner_phone = :phone", nativeQuery = true)
	List<Booking> findBookingByPartnerPhone(String phone);

}
