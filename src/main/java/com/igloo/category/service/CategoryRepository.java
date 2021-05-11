package com.igloo.category.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igloo.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findById(int id);
}
