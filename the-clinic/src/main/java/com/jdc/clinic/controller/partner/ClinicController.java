package com.jdc.clinic.controller.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.services.LocationService;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {

	@Autowired
	LocationService locationService;

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.addAttribute("clinicID", id);
		return "/views/partner/clinic";
	}

	@GetMapping("create")
	public String createClinic(ModelMap model) {
		model.addAttribute("clinic_title", "Add New Clinic");
		model.put("divisions", locationService.findValid());
		return "/views/partner/clinic-edit";
	}

	@PostMapping
	public String save(Clinic clinic) {
		return String.format("redirect:/partner/clinics/%d", clinic.getId());
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {
		model.addAttribute("clinic_title", "Edit (Clinic Name)");
		return "/views/partner/clinic-edit";
	}

	@PostMapping("{id}/delete")
	public String deleteClinicById(@PathVariable int id) {
		return String.format("redirect:/partner/clinics/%d", id);
	}

}
