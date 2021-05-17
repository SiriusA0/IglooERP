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

	public RegionResponse of(Region region) {

		RegionResponse response = new RegionResponse();

		response.setId(region.getId());
		response.setName(region.getName());

		List<City> cities = region.getCities();
		response.setCities(cityAdapter.of(cities));

		Country country = region.getCountry();
		response.setCountry(countryAdapter.of(country));

		return response;

	}

	public List<RegionResponse> of(List<Region> regions) {

		List<RegionResponse> responses = new ArrayList<>();

		for (Region region : regions) {
			responses.add(of(region));
		}
		return responses;

	}

}
