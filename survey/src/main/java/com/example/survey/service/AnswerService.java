package com.example.survey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.entity.Answer;
import com.example.survey.entity.Question;
import com.example.survey.helper.CoreUtil;
import com.example.survey.repository.AnswerRepository;
import com.example.survey.repository.QuestionRepository;


@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public ArrayList<Answer> getAnswerList(){
		ArrayList<Answer> answers = new ArrayList<>();
		answerRepository.findAll();
		return answers;
	}
	
	public Answer getAnswer(Long id) {
		return answerRepository.getById(id);
	}
	
	public List<Answer> getAnswersByCategoryId(Long id) {
		Question question = new Question();
		question = questionRepository.findById(id).get();
		return question.getAnswers();
	}
	
	public void saveAnswer(Answer answer) {
		Answer answer2 = new Answer();
		ArrayList<Answer> answers = new ArrayList<>();
		
		if(!CoreUtil.isEmptyString(answer.getAnswerName())) {
			answer2.setAnswerName(answer.getAnswerName());
		}
		if(CoreUtil.checkPointRange(answer.getPoint())) {
			answer2.setPoint(answer.getPoint());
		}
		if(!CoreUtil.isEmptyObject(answer.getQuestion())) {
			answer2.setQuestion(answer.getQuestion());
		}
		
		answer2 = answerRepository.save(answer2);
		answers.add(answer2);
		answer.getQuestion().setAnswers(answers);
		Optional<Question> optional=questionRepository.findById(answer2.getQuestion().getId());
		if(!optional.isEmpty()) {
			Question question = optional.get();
			question.getAnswers().add(answer2);
			questionRepository.save(question);
		}
		
	}
		
	
}
