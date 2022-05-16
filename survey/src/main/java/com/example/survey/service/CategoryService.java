package com.example.survey.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.entity.Category;
import com.example.survey.repository.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ArrayList<Category> getCategories(){
		ArrayList<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(category -> categories.add(category));
		return categories;
	}
	
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public Category getCategory(Long id) {
		if(id != null) {
			return categoryRepository.getById(id);
		}
		return null;
	}
}
