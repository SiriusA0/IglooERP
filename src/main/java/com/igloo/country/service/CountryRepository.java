package com.igloo.country.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.igloo.country.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
	Country findById(int id); 
}
