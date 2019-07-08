package com.jdc.clinic.controller.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.services.DoctorService;

@Controller
@RequestMapping("/partner/schedules")
public class TimeTableController {
	@Autowired
	DoctorService dService;

	@GetMapping
	public String index(ModelMap model) {
		model.addAttribute("doctors", dService.findAll());
		return "/views/partner/schedules";
	}

	@GetMapping("create")
	public String create(ModelMap model) {
		model.addAttribute("timeTable", dService.getClass());
		return "/views/partner/schedules";
	}

	@PostMapping("upload")
	public String upload() {

		return "/views/partner/schedules";
	}

}
