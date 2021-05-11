package com.igloo.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.igloo.city.service.CityServices;

@Controller
public class CityController {
	
	@Autowired
    private CityServices cityService;
}
