package com.jdc.clinic.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.repo.custom.BaseRepository;

public interface BookingRepo extends BaseRepository<Booking, Long> {

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone", nativeQuery = true)
	List<Booking> findBookingByMemberPhone(String phone);

	@Query(value = "select count(b) from Booking b where b.patient.clinic.owner.phone = :phone and b.bookingDate = :date")
	Long countBookingsByPartnerPhone(String phone, LocalDate date);

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone and date(booking.booking_date)>=date(now())", nativeQuery = true)
	List<Booking> findBookingByMemberPhoneAndDate(String phone);

	@Query(value = "select booking.* from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone= :phone and date(booking.booking_date)=:date", nativeQuery = true)
	List<Booking> findbookingByDate(String phone, LocalDate date);

	@Query(value = "select count(*) from booking join patient on booking.patient_id = patient.id join family_member on family_member.id = patient.family_member_id where member_phone=:phone", nativeQuery = true)
	Long countBookingByMemberPhone(String phone);

	@Query(value = "select booking.* from booking join clinic on booking.clinic_id = clinic.id where clinic.owner_phone = :phone", nativeQuery = true)
	List<Booking> findBookingByPartnerPhone(String phone);

//	@Query(value = "select count(b) from Booking b where b.clinicDoctor.clinic.owner.phone = :phone and b.bookingDate between :startDate and :endDate")
//	PartnerPatientChartDTO findbyBookingDateBetweenAndPartnerPhone(LocalDate startDate, LocalDate endDate,
//			String phone);

	@Query(value = "select booking.* from booking join clinic on booking.clinic_id=clinic.id where clinic.owner_phone=:phone and date(booking.booking_date)>=date(now())", nativeQuery = true)
	List<Booking> findBookingByPartnerPhoneAndDate(String phone);

	@Query(value = "select booking.* from booking join clinic on booking.clinic_id=clinic.id where clinic.owner_phone=:phone and date(booking.booking_date)=:date", nativeQuery = true)
	List<Booking> findByDate(String phone, LocalDate date);

	@Query(value = "select count(b) from Booking b where b.patient.clinic.owner.phone = :phone and b.bookingDate = :date and b.status = 1")
	Long countBookingsByPartnerPhoneToday(String phone, LocalDate date);

}
