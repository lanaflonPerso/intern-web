package com.jdc.clinic.controller;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.services.ClinicDoctorService;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.DoctorService;
import com.jdc.clinic.services.TimeTableService;

@Controller
@RequestMapping("/clinics")
public class PublicClinicController {

	@Autowired
	private ClinicServices service;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private TimeTableService timetableService;

	@Autowired
	private ClinicDoctorService clinicDoctorService;

	@GetMapping
	public String search(@RequestParam(defaultValue = "", required = false) String keyword, ModelMap model) {

		model.put("clinics", service.search(keyword));
		model.put("keyword", keyword);
		model.put("specials", doctorService.getSpecialitiesList());

		return "/views/clinics";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.put("clinic", service.findById(id));
		return "/views/clinic";
	}

	@GetMapping("{id}/schedules")
	public String findSchedulesForClinic(@PathVariable int id, ModelMap model, HttpServletRequest request) {

		model.put("schedules", service.findSchedules(id));
		model.put("clinic", service.findById(id));

		return "/views/schedules";
	}

	@GetMapping("{id}/doctors")
	public String findDoctorsForClinic(@PathVariable int id, ModelMap model) {

		model.put("clinic", service.findById(id));

		List<Doctor> clinicdoctorList = doctorService.getDoctorsByClinicId(id);

		model.addAttribute("clinicdoctorList", clinicdoctorList);
		model.addAttribute("doctorsCount", clinicDoctorService.countDoctorByClinicId(id));
		return "/views/clinicdoctors";
	}

	@GetMapping("{id}/doctors/{doctorId}")
	public String findDoctorsTimetable(@PathVariable("id") int id, @PathVariable("doctorId") int doctorId,
			ModelMap model) {
		model.put("clinic", service.findById(id));

		List<Timetable> doctorsSchedules = timetableService.findDoctorsTimetableByClinicId(id).stream()
				.filter(a -> a.getClinicDoctor().getDoctor().getId() == doctorId).collect(Collectors.toList());
		model.addAttribute("clinicdoctorList", doctorService.getDoctorsByClinicId(id));
		model.addAttribute("doctorsCount", clinicDoctorService.countDoctorByClinicId(id));

		model.addAttribute("doctorSchedules", doctorsSchedules);

		return "/views/clinicdoctors";
	}

	@GetMapping("{clinicID}/schedules/{day}")
	@ResponseBody
	public List<Timetable> getTimeTableByDays(@PathVariable int clinicID, @PathVariable int day) {
		return timetableService.findDoctorsTimetableByClinicIdAndDay(clinicID, DayOfWeek.of(day == 0 ? 7 : day));
	}

}
