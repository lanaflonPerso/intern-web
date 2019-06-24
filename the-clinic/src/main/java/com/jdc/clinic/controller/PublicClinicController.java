package com.jdc.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.services.ClinicServices;

@Controller
@RequestMapping("/clinics")
public class PublicClinicController {
	
	@Autowired
	private ClinicServices service;

	@GetMapping
	public String search(String keyword, ModelMap model) {
		model.put("clinics", service.search(keyword));
		return "/views/clinics";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.put("clinic", service.findById(id));
		return "/views/clinic";
	}

	@GetMapping("{id}/schedules")
	public String findSchedulesForClinic(@PathVariable int id, ModelMap model) {
		model.put("schedules", service.findSchedules(id));
		return "/views/schedules";
	}
}
