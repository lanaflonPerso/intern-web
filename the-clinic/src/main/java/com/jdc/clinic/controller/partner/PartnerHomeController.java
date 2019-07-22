package com.jdc.clinic.controller.partner;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.dto.member.PartnerPatientChartDTO;
import com.jdc.clinic.dto.member.PartnerPatientCount;
import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.services.BookingService;
import com.jdc.clinic.services.PartnerService;
import com.jdc.clinic.services.PatientService;

import lombok.Data;

@Controller
@RequestMapping("/partner/home")
public class PartnerHomeController {

	@Autowired
	PartnerService pService;

	@Autowired
	BookingService bookingService;

	@Autowired
	PatientService patientService;

	LocalDate localDate;

	Partner partner;

	@GetMapping
	public String index(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		Partner p = pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", p);

		partner = (Partner) session.getAttribute("partnerUser");

		model.put("patientCount", pService.getPatientCountByPartnerPhone(partner.getPhone()));
		model.put("bookingCount", pService.getbookingCountByPartnerPhone(partner.getPhone()));
		model.put("doctorCount", pService.getDoctorCountByParterPhone(partner.getPhone()));
		model.put("clinicCount", pService.countClinicByPartner(partner.getPhone()));

		model.put("todayBookingList", bookingService.getBookingByTodayDate(partner.getPhone()));

		localDate = LocalDate.now();
		model.put("todayDate", localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString());

		tempTestChart(model);
		tempDonutChart(model, session);

		return "/views/partner/home";
	}

	@GetMapping("/pie/{num}")
	@ResponseBody
	public PieDTO pieDateMinus(@PathVariable int num) {
		if (num == 0)
			localDate = localDate.minusDays(1);
		else if (num == 1)
			if (!localDate.equals(LocalDate.now()))
				localDate = localDate.plusDays(1);

		PieDTO pieDTO = new PieDTO();
		pieDTO.setDate(localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString());
		pieDTO.setPpcList(
				patientService
							.getPieChartDataByDate(partner.getPhone(), localDate)
							.stream()
							.map(ppc -> ppc.getCount())
							.collect(Collectors.toList()));
		return pieDTO;
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

		List<PartnerPatientCount> ppcList = patientService.getPieChartDataByDate(partner.getPhone(), LocalDate.now());
		model.put("clinicNames", ppcList.stream().map(ppc -> ppc.getName()).collect(Collectors.toList()));
		model.put("pieChartData", ppcList.stream().map(ppc -> ppc.getCount()).collect(Collectors.toList()));

	}

	@Data
	private class PieDTO {
		private String date;
		private List<Long> ppcList;

		public PieDTO() {
			ppcList = new ArrayList<>();
		}
	}

}
