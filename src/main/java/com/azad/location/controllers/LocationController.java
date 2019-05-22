package com.azad.location.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azad.location.entities.Location;

import com.azad.location.service.LocationService;
import com.azad.location.util.EmailUtil;


@Controller
public class LocationController {
	
	@Autowired
	LocationService service;
	
//	@Autowired
//	LocationRepository repository;
	
	@Autowired
	EmailUtil emailUtil;
	
//	@Autowired
//	ReportUtil reportUtil;
//	
//	@Autowired
//	ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		
		Location locationSaved = service.saveLocation(location);
		
		String msg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		
//		emailUtil.sendEmail("springtest1993@gmail.com", "Location Saved", "Location saved successfully and about to return a response.");
		
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		
		Location location = service.getLocationById(id); // retrieve location from database 
		service.deleteLocation(location); 
		
		/*
		 * TO SKIP THE DATABASE CALL:
		 * Location location = new Location();
		 * location.setId(id);
		 * service.deleteLocation(location)
		 * */
		
		// now retrieve all remaining data from database and send them to the browser
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUdpate(@RequestParam("id") int id, ModelMap modelMap) {
		
		service.getLocationById(id);
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		
		return "updateLocation";
	}
	
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		
		return "displayLocations";
	}
	
//	@RequestMapping("/generateReport")
//	public String generateReport() {
//		
//		String path = sc.getRealPath("/");
//		
//		List<Object[]> data = repository.findTypeAndTypeCount();
//		reportUtil.generatePiChart(path, data);
//		
//		return "report";
//	}
}










