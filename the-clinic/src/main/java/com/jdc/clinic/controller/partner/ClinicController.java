package com.jdc.clinic.controller.partner;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Township;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.LocationService;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private ClinicServices clinicService;

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		Clinic c = clinicService.findById(id);
		model.addAttribute("clinic", c);
		return "views/partner/clinic";
	}

	@GetMapping("create")
	public String createClinic(ModelMap model) {
		model.addAttribute("edit_title", "Add New Clinic");
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
	public String save(@RequestParam("phone") List<String> phone, @RequestParam("mails") List<String> email,
			@Valid Clinic clinic, BindingResult result) {

		if (result.hasErrors()) {
			return "views/partner/clinic-edit";
		}

		clinic.setPhone(phone);
		clinic.setMails(email);
		clinic.getAddrress().setTownship(locationService.findTownshipById(clinic.getAddrress().getTownship().getId()));

		Clinic c = clinicService.save(clinic);
		return String.format("redirect:/partner/clinics/%d", c.getId());
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {

		Clinic clinic = clinicService.findById(id);
		model.addAttribute("clinic", clinic);
		model.addAttribute("edit_title", "Edit " + clinic.getName());
		model.put("townships", clinic.getAddrress().getTownship().getDivision().getTownships());

		return "views/partner/clinic-edit";
	}

	@PostMapping("{id}/delete")
	public String deleteClinicById(@PathVariable int id) {
		return String.format("redirect:/partner/clinics/%d", id);
	}

}
