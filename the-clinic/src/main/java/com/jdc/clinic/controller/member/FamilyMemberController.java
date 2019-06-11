package com.jdc.clinic.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.clinic.entity.FamilyMember;

@Controller
@RequestMapping("/member/family")
public class FamilyMemberController {

	@GetMapping("/home")
	public String showAllFamilyMembers(ModelMap model) {

		return "/views/member/home";
	}

	@GetMapping("/create")
	public String create(ModelMap model) {

		return "/views/member/familyMemberCreate";
	}

	@PostMapping("/create")
	public String create(FamilyMember familyMember) {

		return "redirect:/member/family/home";
	}

	@PostMapping("/update")
	public String processupdateFamilyMember(FamilyMember familyMember) {

		return "/views/member/familyMemberUpdateForm";

	}

	@GetMapping("/update/{id}")
	public String updateFamilyMember(@PathVariable("id") int id, ModelMap model) {

		return "redirect:/member/family/home";
	}

	@GetMapping("/search")
	public String searchByName(@PathVariable ("id") int id, @RequestParam("name") String name) {

		return "redirect:/member/family/home";
	}

	@GetMapping("/delete/{id}")
	public String deleteFamilyMember(@PathVariable("id") int id) {

		return "redirect:/member/family/home";
	}

}
