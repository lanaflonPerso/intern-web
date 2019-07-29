package com.jdc.clinic.controller.member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Booking;
import com.jdc.clinic.entity.Booking.Status;
import com.jdc.clinic.entity.Member;
import com.jdc.clinic.entity.Patient;
import com.jdc.clinic.services.BookingService;
import com.jdc.clinic.services.PatientService;
import com.jdc.clinic.services.TimeTableService;

@Controller
@RequestMapping("/member/bookings")
public class MemberBookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	PatientService patientService;

	@Autowired
	TimeTableService timeTableService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		// TODO check Login user or not
		HttpSession session = request.getSession(true);

		model.put("familyMembers",
				bookingService.getFamilyMembersByPhone(((Account) session.getAttribute("loginUser")).getPhone()));

		model.put("clinics", bookingService.findClinics());

		model.put("doctors", bookingService.findDoctors());

		model.put("bookings",
				bookingService.getBookingByMemberAndDate((((Member) session.getAttribute("member")).getPhone())));

		return "/views/member/bookings";
	}

	@GetMapping("/{date}")
	public String showBookingByDate(@PathVariable("date") String date, ModelMap model) {
		model.put("bookings", bookingService.getBookingByDate("member", LocalDate.parse(date)));
		return "/views/member/bookings";
	}

	@GetMapping("/book/{clinicID}/{fmID}/{timeTableID}/{mdate}")
	@ResponseBody
	public String book(@PathVariable int clinicID, @PathVariable int fmID, @PathVariable int timeTableID,
			@PathVariable String mdate) {

		System.out.println(mdate);
		Patient patient = patientService.getPatient(clinicID, fmID);

		Booking booking = new Booking();
		booking.setBookingDate(LocalDate.parse(mdate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		booking.setClinicDoctor(timeTableService.findById(timeTableID).getClinicDoctor());
		booking.setPatient(patient);
		booking.setStatus(Status.Apply);
		booking.setTimeTable(timeTableService.findById(timeTableID));
		bookingService.insertBooking(booking);
		return "Successful";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") long id) {
		bookingService.delete(bookingService.findById(id).get());
		return "redirect:/member/bookings";
	}

	@PostMapping("{id}")
	public String cancel(@PathVariable long id) {
		return "redirect:/member/bookings/**";
	}

}
