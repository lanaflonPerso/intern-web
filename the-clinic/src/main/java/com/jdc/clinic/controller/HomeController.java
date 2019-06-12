package com.jdc.clinic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public String index(HttpServletRequest request, ModelMap model) {
		
		// if admin -> Admin Home
		
		// if member -> Member Home
		
		// if partener -> Partner Home
		
		return "/views/index";
	}
}
