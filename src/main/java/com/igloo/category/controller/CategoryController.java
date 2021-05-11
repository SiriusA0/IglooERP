package com.igloo.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.igloo.category.service.CategoryServices;

@Controller
public class CategoryController {
	
	@Autowired
    private CategoryServices categoryService;
	
}
