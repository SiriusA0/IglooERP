package com.igloo.region.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igloo.city.model.City;
import com.igloo.city.response.CityAdapter;
import com.igloo.country.model.Country;
import com.igloo.country.response.CountryAdapter;
import com.igloo.region.model.Region;



@Component
public class RegionAdapter {
	
	@Autowired
	private CityAdapter cityAdapter;
	@Autowired
	private CountryAdapter countryAdapter;
	
	public RegionResponse of(Region regions) {
		
		RegionResponse response = new RegionResponse();
		
		response.setId(regions.getId());
		response.setName(regions.getName());
		
		List<City> cities = regions.getCities();
		response.setCities(cityAdapter.of(cities));
		
		Country country = regions.getCountry();
		response.setCountry(countryAdapter.of(country));
		
		return response;
		
	}
	
	public List<RegionResponse> of(List<Region> regions){
		
		 List<RegionResponse> responses = new ArrayList<>();
         
	        for(Region region : regions) {
	            responses.add(of(region));
	        }
	        return responses; 
	
	}

}
