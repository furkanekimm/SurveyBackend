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

import com.example.survey.entity.Answer;
import com.example.survey.service.AnswerService;

@RestController
@RequestMapping("/answer")
@CrossOrigin(origins = "*")
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	
	@PostMapping("/add")
	public void saveAnswer(@RequestBody Answer answer) {
		answerService.saveAnswer(answer);
	}
	
	@GetMapping("/{id}")
	public Answer getAnswer(@PathVariable Long id) {
		return answerService.getAnswer(id);
	}
	
	@GetMapping("/")
	public List<Answer> getAnswers(){
		return answerService.getAnswerList();
	}
	
	@GetMapping("/question/{id}")
	public List<Answer> getAnswersByQuestion(@PathVariable Long id){
		return answerService.getAnswersByCategoryId(id);
	}

}
