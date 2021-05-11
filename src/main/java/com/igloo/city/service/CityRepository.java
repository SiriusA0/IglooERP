package com.igloo.city.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.igloo.city.model.City;

public interface CityRepository extends JpaRepository<City, Integer>{
	City findById(int id); 
}