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
		SectorRepository sectorRepository;
		
		@Autowired
        SectorAdapter sectorAdapter;
		
		  public List<SectorResponse> showSector() {
			  
		         List<Sector> sectors = sectorRepository.findAll();
		         
		        return sectorAdapter.of(sectors);
		    }
	
			public Sector createSector(String name) {

				Sector sector = new Sector();
				sector.setName(name);
				sectorRepository.save(sector);
				return sector;
			}

			public void delete(String id) {
			String idArray[] = id.split(",");
	        for (String i : idArray){
	            int idToDelete = Integer.valueOf(i);
	            sectorRepository.deleteById(idToDelete);
	        }
		}	        
}
