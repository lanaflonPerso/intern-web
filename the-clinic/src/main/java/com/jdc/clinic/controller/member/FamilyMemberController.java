package com.jdc.clinic.controller.member;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.dto.member.FMByAge;
import com.jdc.clinic.entity.FamilyMember;
import com.jdc.clinic.entity.Member;
import com.jdc.clinic.services.MemberService;

@Controller
@RequestMapping("/member/family")
public class FamilyMemberController {

	@Autowired
	private MemberService mService;

	@GetMapping("/create")
	public String showCreateForm(ModelMap model) {
		model.addAttribute("familyMember", new FamilyMember());
		return "views/member/family-edit";
	}

	@GetMapping("/list")
	public String showList(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		model.addAttribute("familyMember",
				mService.findAll(((Member) session.getAttribute("member")).getPhone()).stream().map(fm -> {
					FMByAge fmByAge = new FMByAge();
					fmByAge.setName(fm.getName());
					fmByAge.setDob(fm.getDob());
					fmByAge.setGender(fm.getGender());
					fmByAge.setBloodType(fm.getBloodType());
					fmByAge.setId(fm.getId());
					fmByAge.setPhNo(fm.getPhNo());
					fmByAge.setRelationship(fm.getRelationship());
					fmByAge.setAge(Period.between(fm.getDob(), LocalDate.now()).getYears());
					return fmByAge;
				}).collect(Collectors.toList()));

		return "views/member/family";

	}

	@PostMapping("/create")
	public String addFamilyMember(@Valid FamilyMember familyMember, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		familyMember.setMember((Member) session.getAttribute("member"));
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
		return "views/member/family";
	}

	@GetMapping("delete/{id}")
	public String deleteFamilyMember(@PathVariable("id") long id, ModelMap model) {

		FamilyMember familyMember = mService.findMemberById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		mService.delete(familyMember);
		return "redirect:/member/family/list";
	}

}
