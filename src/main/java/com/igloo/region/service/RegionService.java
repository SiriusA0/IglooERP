package com.igloo.region.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;

}
