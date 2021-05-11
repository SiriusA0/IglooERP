package com.igloo.country.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igloo.country.model.Country;

@Service
public class CountryService {
	@Autowired
    private CountryRepository countryRepository;

}
