package com.igloo.region.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.igloo.region.service.RegionService;

@Controller
public class RegionController {
	@Autowired
    private RegionService regionService;
	
}
