package com.jdc.clinic.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.FamilyMember;

@Controller
@RequestMapping("/member/family")
public class FamilyMemberController {

	@GetMapping("/create")
	public String create(ModelMap model) {

		return "/views/member/create-family";
	}

	@GetMapping("/edit/{id}")
	public String editFamilyMember(@PathVariable long id, ModelMap model) {

		return "/views/member/create-family";
	}

	@PostMapping
	public String save(FamilyMember familyMember) {

		return "redirect:/member/home";
	}

	@GetMapping("/delete/{id}")
	public String deleteFamilyMember(@PathVariable long id) {

		return "redirect:/member/home";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable long id, ModelMap model) {

		return "/views/member/family";
	}

}
