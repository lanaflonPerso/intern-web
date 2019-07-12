package com.jdc.clinic.controller.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.services.BookingService;
import com.jdc.clinic.services.PartnerService;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@Autowired
	PartnerService pService;

	@Autowired
	BookingService bookingService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Partner p = pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", p);

		/*
		 * List<Booking> todayBookingList = new ArrayList<Booking>();
		 * 
		 * for (Booking b : bookingService.listAllBookings()) {
		 * 
		 * if (b.getBookingDate().compareTo(LocalDate.now()) == 0) {
		 * todayBookingList.add(b); } }
		 */
		Partner partner = (Partner) session.getAttribute("partnerUser");
		model.addAttribute("patientCount", pService.getPatientCountByPartnerPhone(partner.getPhone()));
		model.addAttribute("bookingCount", pService.getbookingCountByPartnerPhone(partner.getPhone()));
		model.addAttribute("doctorCount", pService.getDoctorCountByParterPhone(partner.getPhone()));
		model.addAttribute("clinicCount", pService.countClinicByPartner(partner.getPhone()));

		// model.addAttribute("todayBookingList", todayBookingList);

		model.addAttribute("todayBookingList", bookingService.getBookingByTodayDate(partner.getPhone()));
		return "/views/partner/home";
	}

}
