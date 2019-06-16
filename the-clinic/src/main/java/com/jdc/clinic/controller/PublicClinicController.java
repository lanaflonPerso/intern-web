package com.jdc.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinics")
public class PublicClinicController {

	@GetMapping
	public String search(String keyword, ModelMap model) {
		return "/views/clinics";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {

		return "/views/partner/clinic";
	}

	@GetMapping("{id}/schedules")
	public String findSchedulesForClinic(@PathVariable int id, ModelMap model) {

		return "/views/schedules";
	}
}
