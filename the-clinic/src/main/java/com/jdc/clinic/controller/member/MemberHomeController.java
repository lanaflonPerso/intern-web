package com.jdc.clinic.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Member;
import com.jdc.clinic.services.MemberEventService;
import com.jdc.clinic.services.MemberService;

@Controller
@RequestMapping("/member/home")
public class MemberHomeController {

	@Autowired
	MemberService service;

	@Autowired
	MemberEventService mService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Member member = service.getMemberByPhone(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("member", member);

		Member m = (Member) session.getAttribute("member");
		model.addAttribute("bookingCount", service.getBookingCountByMemberPhone(m.getPhone()));
		model.addAttribute("memberCount", service.getMemberCountByMemberPhone(m.getPhone()));
		model.addAttribute("clinicCount", service.getClinicCountByMemberPhone(m.getPhone()));
		model.addAttribute("eventCount", service.getEventCountByMemberPhone(m.getPhone()));
		model.addAttribute("events", mService.getEventList());

		return "/views/member/home";
	}

}
