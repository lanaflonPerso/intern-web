package com.jdc.clinic.controller.partner;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.dto.member.PartnerPatientChartDTO;
import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.services.BookingService;
import com.jdc.clinic.services.PartnerService;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@Autowired
	PartnerService pService;

	@Autowired
	BookingService bookingService;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		Partner p = pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", p);

		Partner partner = (Partner) session.getAttribute("partnerUser");
		model.put("patientCount", pService.getPatientCountByPartnerPhone(partner.getPhone()));
		model.put("bookingCount", pService.getbookingCountByPartnerPhone(partner.getPhone()));
		model.put("doctorCount", pService.getDoctorCountByParterPhone(partner.getPhone()));
		model.put("clinicCount", pService.countClinicByPartner(partner.getPhone()));

		model.put("todayBookingList", bookingService.getBookingByTodayDate(partner.getPhone()));

		tempTestChart(model);

		return "/views/partner/home";
	}

	private void tempTestChart(ModelMap model) {

		List<Month> list = new ArrayList<Month>();
		for (int i = 5; i >= 0; i--) {
			YearMonth date = YearMonth.now().minusMonths(i);
			list.add(date.getMonth());

		}

		List<PartnerPatientChartDTO> ppcList = new ArrayList<>();

		PartnerPatientChartDTO ppcDTO = new PartnerPatientChartDTO();
		ppcDTO.setLabel("Clinic A");
		ppcDTO.setData(new int[] { 20, 80, 76, 54, 23, 53 });
		ppcDTO.setBackgroundColor("rgba(255, 99, 132)");
		ppcDTO.setBorderColor("rgba(255, 99, 132, 1)");
		ppcDTO.setBorderWidth(2);
		ppcDTO.setFill(false);

		PartnerPatientChartDTO ppcDTO2 = new PartnerPatientChartDTO();
		ppcDTO2.setLabel("Clinic B");
		ppcDTO2.setData(new int[] { 80, 80, 26, 51, 93, 13 });
		ppcDTO2.setBackgroundColor("rgba(54, 162, 235)");
		ppcDTO2.setBorderColor("rgba(54, 162, 235, 1)");
		ppcDTO2.setBorderWidth(2);
		ppcDTO2.setFill(false);

		ppcList.add(ppcDTO);
		ppcList.add(ppcDTO2);

		model.put("monthList", list);
		model.put("ppcList", ppcList);

	}

}
