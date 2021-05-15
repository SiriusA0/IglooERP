package com.igloo.category.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.igloo.category.model.Category;

@Component
public class CategoryAdapter {

	public CategoryResponse of(Category category) {
		
		CategoryResponse response = new CategoryResponse();
		
		response.setId(category.getId());
		response.setName(category.getName());
	
		return response;
	
	}
	
	public List<CategoryResponse> of(List<Category> categories) {

        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            responses.add(of(category));
        }

        return responses;

    }
	
}
