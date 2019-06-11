package com.jdc.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@PostMapping
	public String signUp() {
		
		return  "redirect:/home";
	}

}
