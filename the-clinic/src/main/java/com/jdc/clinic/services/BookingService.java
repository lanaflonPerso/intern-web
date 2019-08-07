package com.jdc.clinic.services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.clinic.dto.member.ChartDTO;
import com.jdc.clinic.dto.member.LineChartDataSet;
import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.repo.BookingRepo;
import com.jdc.clinic.repo.ClinicDoctorRepo;
import com.jdc.clinic.repo.ClinicRepo;
import com.jdc.clinic.repo.DoctorRepo;
import com.jdc.clinic.repo.FamilyMemberRepo;
import com.jdc.clinic.repo.PatientRepo;

@Service
public class BookingService {
	// private static Logger logger = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	BookingRepo bRepo;

	@Autowired
	FamilyMemberRepo fmRepo;

	@Autowired
	ClinicRepo repo;

	@Autowired
	DoctorRepo docRepo;

	@Autowired
	ClinicDoctorRepo clinicDoctorRepo;

	@Autowired
	PatientRepo patientRepo;

	public Booking insertBooking(Booking booking) {
		return bRepo.save(booking);
	}

	public List<FamilyMember> getFamilyMembersByPhone(String phone) {
		return fmRepo.findByMemberPhone(phone);
	}

	public List<Clinic> findClinics() {
		return repo.findAll();
	}

	public List<Doctor> findDoctors() {
		return docRepo.findAll();
	}

	public List<Booking> listAllBookings() {
		return bRepo.findAll();
	}

	public List<Booking> getBookingsByMember(String memberPhone) {
		return bRepo.findBookingByMemberPhone(memberPhone);

	}

	public List<Booking> getBookingByMemberAndDate(String memberPhone) {
		return bRepo.findBookingByMemberPhoneAndDate(memberPhone);
	}

	public List<Booking> getBookingByDate(String memberPhone, LocalDate date) {
		return bRepo.findbookingByDate(memberPhone, date);
	}

	public List<Booking> getBookingByTodayDate(String partnerPhone) {
		return bRepo.findBookingByPartnerPhone(partnerPhone).stream()
				.filter(a -> ((a.getBookingDate()).compareTo(LocalDate.now())) == 0).collect(Collectors.toList());
	}

	public Optional<Booking> findById(long id) {
		return bRepo.findById(id);
	}

	public void delete(Booking b) {
		bRepo.delete(b);
	}

	public Long findBookingCountbyPhoneAndDate(String phone, LocalDate startDate, LocalDate endDate) {

		Map<String, Object> param = new HashMap<>();
		param.put("phone", phone);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		return bRepo.findCount(
				"select count(b) from Booking b where b.clinicDoctor.clinic.owner.phone = :phone and b.bookingDate between :startDate and :endDate",
				param);

	}

	public ChartDTO setLineChartData(String phone, YearMonth yearMonth) {
		return setLineChartData(phone, yearMonth, 3);
	}

	public ChartDTO setLineChartData(String phone, YearMonth yearMonth, int control) {

		if (control == 0) {
			yearMonth = yearMonth.minusMonths(1);
		} else if (control == 1) {
			yearMonth = yearMonth.plusMonths(1);
		}

		ChartDTO chartDTO = new ChartDTO();

		for (int i = 5; i >= 0; i--) {
			YearMonth ym = yearMonth.minusMonths(i);
			chartDTO.addLabel(ym.format(DateTimeFormatter.ofPattern("MMM yyyy")));
		}

		String[] colorArr = { "rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)", "rgba(255, 206, 86, 1)",
				"rgba(75, 192, 192, 1)", "rgba(153, 102, 255, 1)", "rgba(255, 159, 64, 1)" };

		List<Clinic> clinicList = repo.findByOwnerPhone(phone);

		List<LineChartDataSet> dataSetList = new ArrayList<>();

		for (int i = 0; i < clinicList.size(); i++) {

			Clinic clinic = clinicList.get(i);

			LineChartDataSet dataSet = new LineChartDataSet();

			dataSet.setLabel(clinic.getName());
			List<Long> countList = new ArrayList<>();

			for (int ii = 5; ii >= 0; ii--) {

				YearMonth ym = yearMonth.minusMonths(ii);
				LocalDate startDate = ym.atDay(1);
				LocalDate endDate = ym.atEndOfMonth();

				Map<String, Object> param = new HashMap<>();
				param.put("phone", phone);
				param.put("cID", clinic.getId());
				param.put("startDate", startDate);
				param.put("endDate", endDate);

				countList.add(bRepo.findCount(
						"select count(b) from Booking b where b.clinicDoctor.clinic.owner.phone = :phone and b.bookingDate between :startDate and :endDate and b.clinicDoctor.clinic.id = :cID",
						param));
			}

			dataSet.setData(countList);
			dataSet.setBorderWidth(2);
			dataSet.setBackgroundColor(colorArr[i]);
			dataSet.setBorderColor(colorArr[i]);

			dataSetList.add(dataSet);
		}

		chartDTO.setDataSetList(dataSetList);
		return chartDTO;

	}

	// Partner Booking
	public List<Clinic> getClinicByPhone(String phone) {
		return repo.findByOwnerPhone(phone);
	}

	public List<Booking> getBookingByPartner(String partnerPhone) {
		return bRepo.findBookingByPartnerPhone(partnerPhone);

	}

	public List<Booking> getBookingByPartnerAndDate(String partnerPhone) {
		return bRepo.findBookingByPartnerPhoneAndDate(partnerPhone);

	}

	public List<Booking> getPartnerBookingByDate(String partnerPhone, LocalDate date) {
		return bRepo.findByDate(partnerPhone, date);
	}

}
