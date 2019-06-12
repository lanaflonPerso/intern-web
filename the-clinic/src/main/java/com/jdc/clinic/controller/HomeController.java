package com.jdc.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.utils.AuthHelper;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private AuthHelper auth;
	
	@GetMapping
	public String index(ModelMap model) {
		
		if(auth.isUserInRole("ROLE_Member")) {
			return "redirect:/member/home";
		}
		
		if(auth.isUserInRole("ROLE_Partner")) {
			return "redirect:/partner/home";
		}
		
		return "/views/index";
	}
}
