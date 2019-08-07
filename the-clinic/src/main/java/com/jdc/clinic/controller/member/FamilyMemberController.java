package com.jdc.clinic.controller.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.services.MemberService;

@Controller
@RequestMapping("/member/family")
public class FamilyMemberController {

	@Autowired
	private MemberService mService;

	/*
	 * @Autowired private FamilyMemberService fmService;
	 */

	@GetMapping("/create")
	public String showCreateForm(ModelMap model) {
		model.addAttribute("familyMember", new FamilyMember());
		return "views/member/family-edit";
	}

	/*
	 * @PostMapping("/create") public String createFMember(FamilyMember fmember) {
	 * fmService.createFM(fmember);
	 * 
	 * return "views/member/family"; }
	 */
	@GetMapping("/list")
	public String showList(ModelMap model) {
		model.addAttribute("familyMember", mService.findAll());

		return "views/member/family";

	}

	@PostMapping("/create")
	public String addFamilyMember(@Valid FamilyMember familyMember, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "views/member/family-edit";
		}

		mService.save(familyMember);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showList(@PathVariable("id") long id, ModelMap model) {
		FamilyMember familyMember = mService.findMemberById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Member Id:" + id));
		model.addAttribute("familyMember", familyMember);
		return "views/member/family-edit";
	}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid FamilyMember familyMember, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			familyMember.setId(id);
			return "/views/member/family-update";
		}
		mService.save(familyMember);
		model.addAttribute("students", mService.findAll());
		return "views/member/family";
	}

	@GetMapping("delete/{id}")
	public String deleteFamilyMember(@PathVariable("id") long id, ModelMap model) {

		FamilyMember familyMember = mService.findMemberById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		mService.delete(familyMember);
		model.addAttribute("familyMember", mService.findAll());

		return "redirect:/member/family";
	}

}
