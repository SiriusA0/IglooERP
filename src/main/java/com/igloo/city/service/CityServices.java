package com.igloo.city.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.city.model.City;
import com.igloo.city.response.CityAdapter;
import com.igloo.city.response.CityResponse;
import com.igloo.sector.model.Sector;
import com.igloo.sector.response.SectorResponse;


@Service
public class CityServices {
	@Autowired
    private CityRepository cityRepository;
	@Autowired
	private CityAdapter cityAdapter;
	
	
	public List<CityResponse> search() {

		List<City> cities = cityRepository.findAll();
		return cityAdapter.of(cities);
	}
	
}
