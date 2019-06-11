package com.jdc.clinic.controller.member;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/bookings")
public class MemberBookingController {  
	
	
	@GetMapping("/schedules")
	public String viewSchedu() {
		
		return "views/schedules";
	}
	
	@PostMapping("/book")
	public String book() {
		return "views/bookingForm";
	}
	
	
	@GetMapping("/bookings")
	public String chooseMember(){
		return "views/bookingFrom";
		
	} 
	@PostMapping("/confirm")
		public String confirmBooking() {
			return "views/successBooking";
		}
	}
	
	
	
	
	
	
	
	
