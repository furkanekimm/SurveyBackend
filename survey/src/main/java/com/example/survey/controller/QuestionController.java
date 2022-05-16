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

import com.example.survey.entity.Question;
import com.example.survey.service.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/add")
	public Question saveQuestion(@RequestBody Question question) {
		return questionService.saveQuestion(question);
	}
	
	@GetMapping("/")
	public List<Question> getQuestions(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable Long id) {
		return questionService.getQuestion(id);
	}
	
	@GetMapping("/defaultQuestion/{id}")
	public Question getDefaultQuestionByCategoryId(@PathVariable Long id) {
		return questionService.getDefaultQuestionByCategoryId(id);
	}
	
	@GetMapping("/category/{categoryId}")
	public List<Question> getQuestionsByCategoryId(@PathVariable Long categoryId) {
		return questionService.getQuestionsByCategoryId(categoryId);
	}
	
	@GetMapping("/npm/{categoryId}")
	public List<Question> getQuestionsWithNpmScore(@PathVariable Long categoryId){
		return questionService.getQuestionsWithNpmScore(categoryId);
	}
	
	

}
