package com.igloo.sector.service;

import java.util.List;

import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorAdapter;
import com.igloo.sector.response.SectorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SectorService {
	
		@Autowired
		SectorRepository secorrepository;
		
		@Autowired
        SectorAdapter sectorAdapter;
		
		  public List<SectorResponse> showSector() {
			  
		         List<Sector> sectors = secorrepository.findAll();
		         
		        return sectorAdapter.of(sectors);
		    }
	
		public Sector createSector(String name){
		
		Sector sector = new Sector();
		sector.setName(name);
		
		secorrepository.save(sector);
		
		
		return sector;
	}

}
