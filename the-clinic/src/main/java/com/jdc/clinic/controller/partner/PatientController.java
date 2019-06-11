package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.entity.Patient;

@Controller
@RequestMapping("/partner/patients")
public class PatientController {

	@GetMapping
	public String index() {
		return "view/partner/patients";
	}

	@RequestMapping("/search")
	public String searchPatientByName(@RequestParam("name") String keyword) {
		return "view/partner/patients";
	}

	@GetMapping("/{id}")
	public String detailByID(@PathVariable int id) {
		return "view/partner/patientDetail";
	}

	@PostMapping("/new")
	public String create(Patient patient) {
		return "view/partner/create";
	}

	@GetMapping("/update")
	public String update(int id, Patient patient) {
		return "view/partner/create";
	}

	@RequestMapping("/delete")
	public String deleteByID(@RequestParam("id") int id) {
		return "view/partner/patients";
	}
}
