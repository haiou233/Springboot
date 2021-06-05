package com.kindsonthegenius.fleetapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.services.CountryService;


@Controller
public class CountryController {
	
	@Autowired private CountryService countryService;
	
	
	//Get All Countrys
	@GetMapping("countries")
	public String findAll(Model model){		
		List<Country> countryList = countryService.findAll();
		System.out.println("-------------------");
		System.out.println("list size is "+ countryList.size());
		for(Country country:countryList) {
			//System.out.println(country.id);
		}
		System.out.println("-------------------");
		model.addAttribute("countries", countryService.findAll());
		return "country";  
	}	
	
	@RequestMapping("countries/findById") 
	@ResponseBody
	public Optional<Country> findById(Integer id)
	{
		return countryService.findById(id);
	}
	
	//Add Country
	@PostMapping(value="countries/addNew")
	public String addNew(Country country) {
		countryService.save(country);
		return "redirect:/countries";
	}	
	
	@RequestMapping(value="countries/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Country country) {
		countryService.save(country);
		return "redirect:/countries";
	}
	
	@RequestMapping(value="countries/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String delete(Integer id) {
		countryService.delete(id);
		return "redirect:/countries";
	}
}
