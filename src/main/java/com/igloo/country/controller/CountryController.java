package com.igloo.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.igloo.country.service.CountryService;

@Controller
public class CountryController {
	
	@Autowired
    private CountryService countryService;
	
}
