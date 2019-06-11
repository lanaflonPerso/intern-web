package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/bookings")
public class BookingController {
	
	@GetMapping("/")
	public String showBookingList() {
		
		return "/views/partner/bookings";
	}
	
	
	@GetMapping("/{id}")
	public String selectPatient(@PathVariable int id) {
		
		return "/views/partner/patientDetail";
	}

}
