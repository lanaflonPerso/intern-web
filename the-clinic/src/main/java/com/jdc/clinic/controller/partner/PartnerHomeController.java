package com.jdc.clinic.controller.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.services.PartnerService;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@Autowired
	PartnerService pService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Partner partner = (Partner) session.getAttribute("partnerUser");

		long totalBookingCount = 0;
		long totalPatientCount = 0;
		long totalDoctorCount = 0;
		for (Clinic c : partner.getClinics()) {
			totalBookingCount += pService.getBookingCountByClinicID(c.getId());
			totalPatientCount += pService.getPatientCountByClinicID(c.getId());
			totalDoctorCount += pService.getDoctorCountByClinicID(c.getId());
		}

		model.addAttribute("clinicCount", pService.countClinicByPartner(partner.getPhone()));
		model.addAttribute("bookingCount", totalBookingCount);
		model.addAttribute("patientCount", totalPatientCount);
		model.addAttribute("doctorCount", totalDoctorCount);

		return "/views/partner/home";
	}
}
