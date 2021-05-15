package com.igloo.sector.controller;

import java.util.LinkedList;
import java.util.List;

import com.igloo.agent.model.Agent;
import com.igloo.sector.model.Sector;
import com.igloo.sector.service.SectorRepository;
import com.igloo.sector.response.SectorResponse;
import com.igloo.sector.service.SectorService;
import com.igloo.status.response.StatusResponse;

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
    SectorService sectorService;
	
//	@GetMapping("/api/sector/add")
//    @ResponseBody
//    public List<SectorResponse> add_API(@RequestParam String name) {
//		sectorservice.createSector(name);
//		 
//        return sectorservice.showSector();
//		  
//    }
//	
	@GetMapping("api/sector/add")
	@ResponseBody
	public List<SectorResponse> add_API(@RequestParam(required = false) String id,@RequestParam String name){
	    	
			
			
	    	if(id == null ) {
	    		
	    		sectorService.createSector(name);
	    		
	    	} else {
	    		int idInt=Integer.valueOf(id);
	    		sectorService.editSector(idInt, name);}	
	
	    	return sectorService.showSector();
	}
	
	
	@GetMapping("/api/sector/show")
	@ResponseBody
	public List<SectorResponse> find_API() {
		 
	        return sectorService.showSector();
	}

	@GetMapping("/api/sector/delete")
	@ResponseBody
	public List<SectorResponse> delete_API(@RequestParam String id) {
	  Boolean correct = sectorService.delete(id);
	  List<SectorResponse> sectors;
	  if(correct) {
		  sectors = sectorService.showSector();
	  }else {
		  sectors = new LinkedList<SectorResponse>();
	  }  
	  return sectors;
	}
	
	
	
	
	
}
