package com.jdc.clinic.controller.partner;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.dto.member.ChartDTO;
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
		model.put("localDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString());

		model.put("clinicNames", patientService.getClinicNameListByDate(partner.getPhone(), LocalDate.now()));
		model.put("pieChartData", patientService.getBookingRateListByDate(partner.getPhone(), LocalDate.now()));

		model.put("chartDTO", bookingService.setLineChartData(partner.getPhone(), YearMonth.now(), 3));

		return "/views/partner/home";
	}

	@GetMapping("/pie/{d}/{num}")
	@ResponseBody
	public PieDTO pieDateMinus(@PathVariable String d, @PathVariable int num, HttpServletRequest request) {

		LocalDate localDate = LocalDate.parse(d, DateTimeFormatter.ofPattern("dd MMM yyyy"));

		if (num == 0)
			localDate = localDate.minusDays(1);
		else if (num == 1)
			localDate = localDate.plusDays(1);

		HttpSession session = request.getSession(true);
		Partner partner = (Partner) session.getAttribute("partnerUser");

		PieDTO pieDTO = new PieDTO();
		pieDTO.setDate(localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")).toString());
		pieDTO.setPpcList(patientService.getBookingRateListByDate(partner.getPhone(), localDate));
		return pieDTO;
	}

	@GetMapping("/line/{month}/{num}")
	@ResponseBody
	public ChartDTO lineChartControl(@PathVariable String month, @PathVariable int num, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Partner partner = (Partner) session.getAttribute("partnerUser");

		return bookingService.setLineChartData(partner.getPhone(),
				YearMonth.parse(month.replace(month.substring(1, 3), month.substring(1, 3).toLowerCase()),
						DateTimeFormatter.ofPattern("MMM yyyy")),
				num);
	}

	@Data
	private class PieDTO {
		private String date;
		private List<Long> ppcList;
	}

}
