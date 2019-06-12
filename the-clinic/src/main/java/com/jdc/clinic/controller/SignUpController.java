package com.jdc.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@GetMapping
	public String index() {
		return "/views/signup";
	}
	
	@PostMapping
	public String signUp() {
		
		return  "redirect:/home";
	}

}
