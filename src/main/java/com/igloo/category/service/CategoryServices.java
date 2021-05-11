package com.igloo.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices {
	
	@Autowired
    private CategoryRepository categoryRepository;
	
}
