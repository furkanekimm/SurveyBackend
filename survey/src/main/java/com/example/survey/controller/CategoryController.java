package com.example.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.entity.Category;
import com.example.survey.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
	@Autowired	
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public void saveCategory(@RequestBody Category category) {
		categoryService.saveCategory(category);
	}
	
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryService.getCategory(id);
	}
	
	@GetMapping("/")
	public List<Category> getCategories(){
		return categoryService.getCategories();
	}
	

}
