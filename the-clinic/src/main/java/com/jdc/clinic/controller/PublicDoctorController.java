package com.jdc.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class PublicDoctorController {
	@GetMapping
	public String search(String keyword,ModelMap model) {
		return "/view/speciality/doctorlist";
	}

}
