package com.jdc.clinic.controller.partner;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.jdc.clinic.services.PatientService;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@Autowired
	PartnerService pService;

	@Autowired
	BookingService bookingService;

	@Autowired
	PatientService patientService;

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
		tempDonutChart(model, session);

		return "/views/partner/home";
	}

	private void tempTestChart(ModelMap model) {

		int[] iArr = new int[] { 0, 0, 0, 0, 0, 0 };
		iArr[5] = 53;

		List<Month> list = new ArrayList<Month>();

		for (int i = 5; i >= 0; i--)
			list.add(YearMonth.now().minusMonths(i).getMonth());

		List<PartnerPatientChartDTO> ppcList = new ArrayList<>();

		PartnerPatientChartDTO ppcDTO = new PartnerPatientChartDTO();
		ppcDTO.setLabel("Clinic A");
		ppcDTO.setData(iArr);
		ppcDTO.setBackgroundColor("rgba(255, 99, 132, 1)");
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

	private void tempDonutChart(ModelMap model, HttpSession session) {

		Partner partner = (Partner) session.getAttribute("partnerUser");

		List<String> clinicNames = partner.getClinics().stream().map(c -> c.getName()).collect(Collectors.toList());

		model.put("clinicNames", clinicNames);

		model.put("pieChartData", patientService.getPieChartData(partner.getPhone()).stream().map(ppc -> ppc.getCount())
				.collect(Collectors.toList()));

	}

}
