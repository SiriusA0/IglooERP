package com.igloo.region.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.country.model.Country;
import com.igloo.country.response.CountryResponse;
import com.igloo.region.model.Region;
import com.igloo.region.response.RegionAdapter;
import com.igloo.region.response.RegionResponse;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private RegionAdapter regionAdapter;
	
	public List<RegionResponse> search() {

		List<Region> regions = regionRepository.findAll();
		return regionAdapter.of(regions);
	}

}
