package com.igloo.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igloo.category.model.Category;
import com.igloo.category.response.CategoryAdapter;
import com.igloo.category.response.CategoryResponse;


@Service
public class CategoryService {
	
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
	private CategoryAdapter categoryAdapter;
	
	public List<CategoryResponse> search() {

		List<Category> categories = categoryRepository.findAll();
		return categoryAdapter.of(categories);
	}
	
}
