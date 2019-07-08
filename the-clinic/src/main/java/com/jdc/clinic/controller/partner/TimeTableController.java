package com.jdc.clinic.controller.partner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.services.DoctorService;
import com.jdc.clinic.services.TimeTableService;

@Controller
@RequestMapping("/partner/schedules")
public class TimeTableController {
	@Autowired
	private DoctorService dService;

	@Autowired
	private TimeTableService timeTableService;

	@GetMapping
	public String index(ModelMap model) {
		model.addAttribute("timetables", timeTableService.findAll());
		model.addAttribute("doctors", dService.findAll());
		model.addAttribute("tTable", new Timetable());
		return "/views/partner/schedules";
	}

//	@PostMapping("create")
//	public String save(ModelMap model) {
//
//	}

}
