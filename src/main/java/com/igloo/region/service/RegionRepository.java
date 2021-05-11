package com.igloo.region.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igloo.region.model.Region;

public interface RegionRepository extends JpaRepository<Region, Integer>{
	Region findById(int id); 
}
