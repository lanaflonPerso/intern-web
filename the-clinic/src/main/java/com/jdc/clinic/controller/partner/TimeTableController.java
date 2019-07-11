package com.jdc.clinic.controller.partner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.clinic.entity.ClinicDoctor;
import com.jdc.clinic.entity.Timetable;
import com.jdc.clinic.services.ClinicDoctorService;
import com.jdc.clinic.services.TimeTableService;

@Controller
@RequestMapping("/partner/schedules")
public class TimeTableController {

	@Autowired
	private ClinicDoctorService cDService;

	@Autowired
	private TimeTableService timeTableService;

	private List<ClinicDoctor> clinicDoctorList;

	@GetMapping
	public String index(ModelMap model) {
		clinicDoctorList = cDService.findAll();
		model.addAttribute("timetables", timeTableService.findAll());
		model.addAttribute("clinicDoctors", clinicDoctorList);
		model.addAttribute("tTable", new Timetable());
		return "/views/partner/schedules";
	}

	@PostMapping("/create")
	public String save(Timetable tt) {
		clinicDoctorList.stream().filter(cd -> cd.getId().getDoctorId() == tt.getClinicDoctor().getDoctor().getId())
				.findFirst().ifPresent(cd -> {
					tt.getClinicDoctor().setId(cd.getId());
				});
		timeTableService.save(tt);
		return "redirect:/partner/schedules";
	}

}
