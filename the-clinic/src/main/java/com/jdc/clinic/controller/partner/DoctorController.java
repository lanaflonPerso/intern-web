package com.jdc.clinic.controller.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.services.DoctorService;

@Controller
@RequestMapping("/partner/doctors")
public class DoctorController {

	@Autowired
	DoctorService dService;

	@GetMapping("{clinicId}")
	public String index(@PathVariable int clinicId, ModelMap model) {
		model.put("doctors", dService.getDoctorsByClinicId(clinicId));
		return "/views/partner/doctors";
	}

	@GetMapping("{clinicId}/create")
	public String create(@PathVariable int clinicId) {

		return "/views/partner/doctor-edit";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable int id) {

		return "/views/partner/doctor-edit";
	}

	@PostMapping("save")
	public String save(Doctor doctor) {
		return String.format("redirect:/partner/doctors/details/%d", doctor.getId());
	}

	@GetMapping("details/{id}")
	public String findById(@PathVariable int id) {

		return "/views/partner/doctor";
	}

	@PostMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		return String.format("redirect:/partner/doctors/details/%d", id);
	}
}
