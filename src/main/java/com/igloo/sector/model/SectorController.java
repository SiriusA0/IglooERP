package com.igloo.sector.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igloo.agent.model.Agent;

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
	    public List<Sector> find_API() {
		 
	        return sectorservice.showSector();
	    }

}
