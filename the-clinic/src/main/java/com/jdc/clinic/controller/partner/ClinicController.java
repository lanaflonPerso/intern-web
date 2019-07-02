package com.jdc.clinic.controller.partner;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.clinic.entity.Clinic;
import com.jdc.clinic.entity.Partner;
import com.jdc.clinic.entity.Township;
import com.jdc.clinic.services.ClinicServices;
import com.jdc.clinic.services.LocationService;

@Controller
@RequestMapping("/partner/clinics")
public class ClinicController {

	@Autowired
	LocationService locationService;

	@Autowired
	ClinicServices clinicService;

	@GetMapping("{id}")
	public String findById(@PathVariable int id, ModelMap model) {
		model.addAttribute("clinicID", id);
		return "views/partner/clinic";
	}

	@GetMapping("create")
	public String createClinic(ModelMap model) {
		model.addAttribute("clinic_title", "Add New Clinic");
		model.put("divisions", locationService.findValid());
		model.put("townships", locationService.findTownships(0));

		model.addAttribute("clinic", new Clinic());
		return "views/partner/clinic-edit";
	}

	@GetMapping("division/{id}")
	@ResponseBody
	public String getTownshipByDivision(@PathVariable("id") int divID, ModelMap model) {

		List<Township> townshipList = locationService.findTownships(divID);
		String temp = "";
		for (Township t : townshipList) {
			temp += "<option value=\"" + t.getId() + "\">" + t.getName() + "</option>\n";
		}
		return temp;
	}

	@PostMapping
	public String save(@RequestParam("phone") List<String> phone, @RequestParam("mails") List<String> email,
			@Valid Clinic clinic, BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "views/partner/clinic-edit";
		}

		HttpSession session = request.getSession(true);

		clinic.setPhone(phone);
		clinic.setMails(email);
		clinic.setOwner(((Partner) session.getAttribute("partnerUser")));

		Clinic c = clinicService.save(clinic);

		((Partner) session.getAttribute("partnerUser")).getClinics().add(c);

		return String.format("redirect:/partner/clinics/%d", c.getId());
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, ModelMap model) {
		model.addAttribute("clinic_title", "Edit (Clinic Name)");
		return "views/partner/clinic-edit";
	}

	@PostMapping("{id}/delete")
	public String deleteClinicById(@PathVariable int id) {
		return String.format("redirect:/partner/clinics/%d", id);
	}

}
