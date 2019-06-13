package com.jdc.clinic.controller.advices;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.clinic.entity.Division;
import com.jdc.clinic.services.LocationService;

@Scope("application")
@ControllerAdvice
public class LocationsProducers {

	@Autowired
	private LocationService service;
	
	private List<Division> divisions;
	
	@PostConstruct
	private void init() {
		divisions = service.findValid();
	}
	
	@ModelAttribute("divisions")
	public List<Division> divisions() {
		return divisions;
	}
}
