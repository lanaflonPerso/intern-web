package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/doctors")
public class DoctorController {
	
	@GetMapping
	public String index() {
		
		return "/views/partner/doctors";
	}
	
	@PostMapping
	public String create() {
	
		return "/views/partner/doctors";
	}

	@GetMapping("/{id}")
	public String selectDoctor(@PathVariable int id) {
		return "view/partner/doctorDetail";
	}
	

}
