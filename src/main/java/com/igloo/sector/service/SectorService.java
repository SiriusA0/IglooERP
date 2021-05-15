package com.igloo.sector.service;

import java.util.List;

import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorAdapter;
import com.igloo.sector.response.SectorResponse;
import com.igloo.status.model.Status;

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
			
			public Boolean delete(String id) {
				Boolean correct = false;
				try {
					int idToDelete = Integer.valueOf(id);
					sectorRepository.deleteById(idToDelete);
					correct = true;
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		        return correct;
	        }			
			
			public void editSector(Integer id, String name) {
				
				Sector sector = sectorRepository.findById(id).get();
				sector.setName(name);
				sectorRepository.save(sector);
			}
}
