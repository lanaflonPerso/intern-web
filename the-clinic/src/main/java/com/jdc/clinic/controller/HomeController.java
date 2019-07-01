package com.jdc.clinic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.services.AccountService;
import com.jdc.clinic.services.PartnerService;
import com.jdc.clinic.utils.AuthHelper;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private AuthHelper auth;

	@Autowired
	private AccountService service;

	@Autowired
	private PartnerService pService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginUser") == null) {
			Account account = service.findByLoginId(auth.getLoginId());
			session.setAttribute("loginUser", account);
		}

		if (auth.isUserInRole("ROLE_Member")) {
			return "redirect:/member/home";
		}

		if (auth.isUserInRole("ROLE_Partner")) {
			if (session.getAttribute("partnerUser") == null) {
				session.setAttribute("partnerUser",
						pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone()));
			}

			return "redirect:/partner/home";
		}

		return "/views/index";
	}
}
