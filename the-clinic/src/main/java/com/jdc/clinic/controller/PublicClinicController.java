package com.jdc.clinic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.Doctor;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.DoctorService;

@Controller
@RequestMapping("/clinics")
public class PublicClinicController {

	@Autowired
	private ClinicServices service;

	@Autowired
	private DoctorService doctorService;

	@GetMapping
	public String search(String keyword, ModelMap model) {
		model.put("clinics", service.search(keyword));

		return "/views/clinics";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.put("clinic", service.findById(id));
		return "/views/clinic";
	}

	@GetMapping("{id}/schedules")
	public String findSchedulesForClinic(@PathVariable int id, ModelMap model, LocalDate todaydate) {
		model.put("schedules", service.findSchedules(id));
		List<Timetable> doctorsSchedules = doctorService.findSchedulesByDoctor(id).stream()
				.filter(a -> a.getDay() == todaydate.getDayOfWeek()).collect(Collectors.toList());
		model.addAttribute("timetable", doctorsSchedules);

		return "/views/schedules";
	}

	@GetMapping("{id}/doctors")
	public String findDoctorsForClinic(@PathVariable int id, ModelMap model) {

		List<Doctor> clinicdoctorList = doctorService.getDoctorsByClinicId(id);

		model.addAttribute("clinicdoctorList", clinicdoctorList);

		return "/views/clinicdoctors";
	}

	@GetMapping("{id}/doctors/{doctorId}")
	public String findDoctorsTimetable(@PathVariable("id") int id, @PathVariable("doctorId") int doctorId,
			ModelMap model) {

		List<Timetable> doctorsSchedules = doctorService.findSchedulesByDoctor(id).stream()
				.filter(a -> a.getClinicDoctor().getDoctor().getId() == doctorId).collect(Collectors.toList());
		model.addAttribute("clinicdoctorList", doctorService.getDoctorsByClinicId(id));
		model.addAttribute("doctorSchedules", doctorsSchedules);

		return "/views/clinicdoctors";
	}

}
