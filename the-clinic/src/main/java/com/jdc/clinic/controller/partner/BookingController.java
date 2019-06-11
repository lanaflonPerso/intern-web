package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/bookings")
public class BookingController {

	@GetMapping("{clinicID}")
	public String index(@PathVariable int clinicID, ModelMap model) {

		return "/views/partner/bookings";
	}

	@GetMapping("{clinicID}/search")
	public String search(@PathVariable int clinicID, ModelMap model) {
		// TODO Need To consider search parameters
		return "/views/partner/bookings";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable int id) {

		return "/views/partner/patient";
	}

	@PostMapping("/{id}")
	public String cancel(@PathVariable int id) {

		return String.format("redirect:/partner/bookings/%d", id);
	}
}
