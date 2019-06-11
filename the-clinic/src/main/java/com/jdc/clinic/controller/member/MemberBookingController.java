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
		return "view/bookingForm";
	}
	
	
	@GetMapping("/bookings")
	public String chooseMember(){
		return "view/bookingFrom";
		
	} 
	@PostMapping("/confirm")
		public String confirmBooking() {
			return "view/successBooking";
		}
	}
	
	
	
	
	
	
	
	
