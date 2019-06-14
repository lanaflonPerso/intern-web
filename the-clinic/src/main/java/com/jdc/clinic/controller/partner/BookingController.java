package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/partner/{clinicID}/bookings")
public class BookingController {

	@GetMapping
	public String index(@PathVariable int clinicID, ModelMap model) {

		return "/views/partner/bookings";
	}

	@GetMapping("/search")
	public String search(@PathVariable int clinicID, @RequestParam("pname") String pname, ModelMap model) {
		// TODO Need To consider search parameters
		return "/views/partner/bookings";
	}

	@GetMapping("/{bookid}")
	public String findById(@PathVariable int clinicID, @PathVariable int bookid, ModelMap model) {

		return "/views/partner/patient";
	}

	@PostMapping("/{bookid}")
	public String cancel(@PathVariable int clinicID, @PathVariable int bookid) {

		return String.format("redirect:/partner/bookings/%d", bookid);
	}
}
