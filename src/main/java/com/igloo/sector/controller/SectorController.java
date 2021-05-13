package com.igloo.sector.controller;

import java.util.List;

import com.igloo.sector.model.Sector;
import com.igloo.sector.service.SectorRepository;
import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SectorController {
	
	@Autowired
    SectorRepository sectorrepository;
	
	@Autowired
    SectorService sectorservice;
	
	@GetMapping("/api/sector/add")
    @ResponseBody
    public Sector add_API(@RequestParam String name) {

        return sectorservice.createSector(name);
    }
	
	 @GetMapping("/api/sector/show")
	 @ResponseBody
	 public List<SectorResponse> find_API() {
		 
	        return sectorservice.showSector();
	    }

}
