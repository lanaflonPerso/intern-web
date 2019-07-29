package com.jdc.clinic.controller.partner;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

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

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.OpenTime;
import com.jdc.clinic.entity.Township;
import com.jdc.clinic.services.ClinicDoctorService;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.LocationService;
import com.jdc.clinic.services.OpenTimeService;

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

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {

		model.put("clinic", clinicService.findById(id));
		model.put("days", Arrays.asList(DayOfWeek.values()));
		model.put("doctorList", clinicDoctorService.getDoctorsByClinicId(id));

		return "views/partner/clinic";
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

	@PostMapping("{id}/delete")
	public String deleteClinicById(@PathVariable int id) {
		return String.format("redirect:/partner/clinics/%d", id);
	}

	@GetMapping("24hr/{id}")
	@ResponseBody
	public List<OpenTime> set24Hr(@PathVariable int id) {
		for (DayOfWeek day : DayOfWeek.values())
			openTimeService.set24hrByClinicId(id, day);
		return clinicService.findById(id).getOpenTime();
	}
}
