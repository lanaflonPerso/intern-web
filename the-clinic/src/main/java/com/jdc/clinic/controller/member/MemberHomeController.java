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
import com.jdc.clinic.services.MemberService;

@Controller
@RequestMapping("/member/home")
public class MemberHomeController {

	@Autowired
	MemberService service;

	/*
	 * @Autowired MemberEventService mService;
	 */

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Member member = service.getMemberByPhone(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("member", member);

		member.getFamily().stream().map(m -> m.getName()).forEach(System.out::println);

		model.addAttribute("bookingCount", service.getBookingCountByMemberPhone(member.getPhone()));
		model.addAttribute("memberCount", service.getMemberCountByMemberPhone(member.getPhone()));
		model.addAttribute("clinicCount", service.getClinicCountByMemberPhone(member.getPhone()));
		/*
		 * model.addAttribute("eventCount",
		 * service.getEventCountByMemberPhone(m.getPhone()));
		 */
		/* model.addAttribute("events", mService.getEventList()); */

		return "/views/member/home";
	}

}
