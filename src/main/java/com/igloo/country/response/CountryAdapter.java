package com.igloo.country.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.igloo.country.model.Country;

@Component
public class CountryAdapter {
	
	public CountryResponse of(Country country) {
		
		CountryResponse response = new CountryResponse();
		
		response.setId(country.getId());
		response.setName(country.getName());
		
		//Falta region
		
		
		return response;
	}
	
	public List<CountryResponse> of(List<Country> countries){
		
		List<CountryResponse> responses = new ArrayList<>();
        for(Country country : countries) {
            responses.add(of(country));
        }
        return responses;      
		
		
	}
 	

}
