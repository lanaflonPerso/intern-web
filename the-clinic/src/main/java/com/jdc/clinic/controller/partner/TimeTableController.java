package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/schedules")
public class TimeTableController {
	
	@GetMapping
	public String index() {
		
		return "/views/partner/schedules";
	}
	
	@PostMapping
	public String create() {
	
		return "/views/partner/schedules";
	}

	@PostMapping("upload")
	public String upload() {
	
		return "/views/partner/schedules";
	}
	
	
}
