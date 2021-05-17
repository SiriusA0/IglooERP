package com.igloo.country.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.city.model.City;
import com.igloo.city.response.CityResponse;
import com.igloo.country.model.Country;
import com.igloo.country.response.CountryAdapter;
import com.igloo.country.response.CountryResponse;

@Service
public class CountryService {
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
	private CountryAdapter countryAdapter;
	
	public List<CountryResponse> search() {

		List<Country> countries = countryRepository.findAll();
		return countryAdapter.of(countries);
	}

}
