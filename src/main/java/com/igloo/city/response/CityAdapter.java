package com.igloo.city.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.city.model.City;

@Component
public class CityAdapter {
	
	public CityResponse of(City city) {
		
		CityResponse response = new CityResponse();
		
		response.setId(city.getId());
		response.setName(city.getName());
		
		return response;
	}
	
	public List<CityResponse> of(List<City> cities) {
        
        List<CityResponse> responses = new ArrayList<>();
        for(City city : cities) {
            responses.add(of(city));
        }
        
        return responses;      
    }

}
