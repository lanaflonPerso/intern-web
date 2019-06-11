package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {
	
	@GetMapping("/create")
	public String createClinic() {
		return "/views/partner/clinic_create";
		
	}
	
	@PostMapping("/retrieve")
	public String retrieveClinicById(int id) {
		return "/views/partner/clinic_retrieve";
	}
	
	
	@PostMapping("/update")
	public String updateClinicById(int id) {
		return "/views/partner/clinic_update";
	}
	
	
	@PostMapping("/delete")
	public String deleteClinicById(int id) {
		return "/views/partner/clinic_delete";
		
	}	
	

}
