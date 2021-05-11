package com.igloo.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.city.model.City;


@Service
public class CityServices {
	@Autowired
    private CityRepository cityRepository;
}
