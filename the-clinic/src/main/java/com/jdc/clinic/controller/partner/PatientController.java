package com.jdc.clinic.controller.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.PartnerService;
import com.jdc.clinic.services.PatientService;

@Controller
@RequestMapping("/partner/{clinicID}/patients")
public class PatientController {

	@Autowired
	PatientService pService;

	@Autowired
	ClinicServices cService;

	@Autowired
	PartnerService partnerService;

	@GetMapping
	public String index(@PathVariable int clinicID, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Partner p = partnerService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", p);

		Partner partner = (Partner) session.getAttribute("partnerUser");

		model.addAttribute("clinics", cService.findByOwnerPhone(partner.getPhone()));
		// model.addAttribute("patients", pService.findPatientByClinicId(clinicID));
		model.addAttribute("patients", pService.getAllPatient());

		return "/views/partner/patients";
	}

	@RequestMapping
	public String searchPatientByName(@PathVariable int clinicID, @RequestParam String word, ModelMap model) {
		return "/views/partner/patients";
	}

	/*
	 * @GetMapping("/{id}") public String detailByID(@PathVariable int
	 * clinicID, @PathVariable long id, ModelMap model) { // TODO patient info by
	 * clinic return "/views/partner/patient"; }
	 */

	@GetMapping("/{id}")
	public String detail(@PathVariable long id, ModelMap model) {
		model.put("patient", pService.findById(id));
		return "/views/partner/patient";
	}
}
