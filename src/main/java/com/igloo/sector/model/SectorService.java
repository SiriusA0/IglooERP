package com.igloo.sector.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SectorService {
	
		@Autowired
		SectorRepository secorrepository;
	
		public Sector createSector(String name){
		
		Sector sector = new Sector();
		sector.setName(name);
		
		secorrepository.save(sector);
		
		
		return sector;
	}

}
