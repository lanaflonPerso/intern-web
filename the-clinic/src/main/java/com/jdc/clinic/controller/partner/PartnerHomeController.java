package com.jdc.clinic.controller.partner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@GetMapping
	public String index(ModelMap model) {
		
		return "/views/partner/home";
	}
}
