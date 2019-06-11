package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.entity.Clinic;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {
	
	@GetMapping("/create")
	public String createClinic(Clinic clinic) {
		//TODO
		return "/views/partner/clinic_create";
		
	} 
	
	@PostMapping("/retrieve")
	public String retrieveClinicById(int id) {
		//TODO
		return "/views/partner/clinic_retrieve";
	}
	
	
	@PostMapping("/update")
	public String updateClinicById(int id) {
		//TODO
		return "/views/partner/clinic_update";
	}
	
	
	@PostMapping("/delete")
	public String deleteClinicById(int id) {
		//TODO
		return "/views/partner/clinic_delete";
		
	}
	
	
	@GetMapping("{id}")
	public String searchByName(@PathVariable int id,@RequestParam("name") String name) {
		//TODO 
		return "redirect:/partner/clinic_name";
	}
	
}
