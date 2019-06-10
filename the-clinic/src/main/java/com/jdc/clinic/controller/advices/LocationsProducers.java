package com.jdc.clinic.controller.advices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.clinic.entity.Division;
import com.jdc.clinic.services.LocationService;

@ControllerAdvice
public class LocationsProducers {

	@Autowired
	private LocationService service;
	
	@ModelAttribute("divisions")
	public List<Division> divisions() {
		return service.findValid();
	}
}
