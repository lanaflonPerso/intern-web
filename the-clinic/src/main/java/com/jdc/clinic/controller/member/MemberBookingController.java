package com.jdc.clinic.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/bookings")
public class MemberBookingController {

	@GetMapping
	public String index(ModelMap model) {
		// TODO check Login user or not
		return "/views/member/bookings";
	}

	@PostMapping
	public String book() {
		// TODO need to consider parameter
		return "redirect:/member/bookings/**";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable long id, ModelMap model) {
		return "/views/member/booking";
	}

	@PostMapping("{id}")
	public String cancel(@PathVariable long id) {
		return "redirect:/member/bookings/**";
	}
}
