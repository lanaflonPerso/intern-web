package com.jdc.clinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Account.Role;
import com.jdc.clinic.entity.Member;
import com.jdc.clinic.services.MemberService;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MemberService service;

	@GetMapping
	public String index(ModelMap map) {
		map.addAttribute("member", new Member());
		return "/views/signup";
	}

	@PostMapping
	public String signUp(@Valid Member member, BindingResult result) {
		if (result.hasErrors()) {
			return "views/signup";
		}

		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole(Role.ROLE_Member);
		service.saveMember(member);
		return "redirect:/home";
	}

}
