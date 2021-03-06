package com.jdc.clinic.controller.partner;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.entity.Account;
import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.OpenTime;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.entity.Township;
import com.jdc.clinic.services.ClinicDoctorService;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.LocationService;
import com.jdc.clinic.services.OpenTimeService;
import com.jdc.clinic.services.PartnerService;
import com.jdc.clinic.services.TimeTableService;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private ClinicServices clinicService;

	@Autowired
	private OpenTimeService openTimeService;

	@Autowired
	private ClinicDoctorService clinicDoctorService;

	@Autowired
	private TimeTableService timeTableService;

	@Autowired
	private PartnerService pService;

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		Partner partner = pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", partner);

		model.put("clinic", clinicService.findById(id));
		model.put("days", Arrays.asList(DayOfWeek.values()));
		model.put("doctorList", clinicDoctorService.getDoctorsByClinicId(id));
		model.put("timetableDTO", timeTableService.findTimeTableDTO(id));
		model.put("doctorTimetable", timeTableService.getDoctorTimeTable(id));

		return "views/partner/clinic";
	}

	@GetMapping("byday/{id}/{day}")
	@ResponseBody
	public List<Timetable> findTimeTableByDay(@PathVariable int id, @PathVariable int day) {
		return timeTableService.findDoctorsTimetableByClinicIdAndDay(id, DayOfWeek.of(day + 1));
	}

	@GetMapping("create")
	public String createClinic(ModelMap model) {
		model.put("divisions", locationService.findValid());
		model.put("townships", locationService.findTownships(1));

		model.addAttribute("clinic", new Clinic());
		return "views/partner/clinic-edit";
	}

	@GetMapping("division/{divID}")
	@ResponseBody
	public List<Township> getTownshipByDivision(@PathVariable int divID) {
		return locationService.findTownships(divID);
	}

	@PostMapping
	public String save(@Valid Clinic clinic, BindingResult result) {

		if (result.hasErrors()) {
			return "views/partner/clinic-edit";
		}

		Clinic c = clinicService.save(clinic);
		return String.format("redirect:/partner/clinics/%d", c.getId());
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {
		Clinic clinic = clinicService.findById(id);

		model.put("clinic", clinic);
		model.put("townships", clinic.getAddrress().getTownship().getDivision().getTownships());

		return "views/partner/clinic-edit";
	}

	@GetMapping("{id}/delete")
	public String deleteClinicById(@PathVariable int id, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		Partner partner = pService.getPartner(((Account) session.getAttribute("loginUser")).getPhone());
		session.setAttribute("partnerUser", partner);

		clinicService.delete(id);
		return "redirect:/home";
	}

	@GetMapping("24hr/{id}")
	@ResponseBody
	public List<OpenTime> set24Hr(@PathVariable int id) {
		for (DayOfWeek day : DayOfWeek.values())
			openTimeService.set24hrByClinicId(id, day);
		return clinicService.findById(id).getOpenTime();
	}
}
