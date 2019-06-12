package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/partner/{clinicID}/patients")
public class PatientController {

	@GetMapping
	public String index(@PathVariable int clinicID, ModelMap model) {
		// TODO need to check own clinic
		return "/views/partner/patients";
	}

	@RequestMapping
	public String searchPatientByName(@PathVariable int clinicID, @RequestParam String word, ModelMap model) {
		return "/views/partner/patients";
	}

	@GetMapping("/{id}")
	public String detailByID(@PathVariable int clinicID, @PathVariable long id, ModelMap model) {
		// TODO patient info by clinic
		return "/views/partner/patient";
	}
}
