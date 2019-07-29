package com.jdc.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.services.DoctorService;

@Controller
@RequestMapping("/doctors")
public class PublicDoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping
	public String searchByDoctor(@RequestParam(defaultValue = "") String keyword, ModelMap model) {
		model.addAttribute("publicdoctors", doctorService.findAll());
		return "/views/doctors";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable int id) {
		// TODO nearest ten schedules,Doctor's schedule
		return "/views/doctor";
	}

}
