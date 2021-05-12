package com.igloo.sector.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SectorAdapter {
	
	public SectorResponse of(Sector sector) {
		
		SectorResponse response = new SectorResponse();
		
		
		
		response.setId(sector.getId());
		response.setName(sector.getName());
		
		return response;
        
	}
	
	public List<SectorResponse> of(List<Sector> sectors){
		
		
		List<SectorResponse> responses = new ArrayList<>();
		
		for(Sector sector : sectors) {
			
			
			responses.add(of(sector));
		}
		
		return responses;
		
	}

}
