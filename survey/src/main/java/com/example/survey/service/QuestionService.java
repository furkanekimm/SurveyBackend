package com.example.survey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.survey.entity.Answer;
import com.example.survey.entity.Category;
import com.example.survey.entity.Question;
import com.example.survey.helper.CoreUtil;
import com.example.survey.repository.CategoryRepository;
import com.example.survey.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Question> getAllQuestions() {
		List<Question> questions = questionRepository.findAll();
		return questions;
	}

	public Question getQuestion(Long id) {
		return questionRepository.getById(id);
	}

	public List<Question> getQuestionsByCategoryId(Long categoryId) {
		ArrayList<Question> questions = new ArrayList<>();
		categoryRepository.findById(categoryId).get().getQuestions().forEach(question -> questions.add(question));
		return questions;
	}

	public Question getDefaultQuestionByCategoryId(Long categoryId) {
		Question question = new Question();
		question = categoryRepository.findById(categoryId).get().getQuestions().get(0);
		return question;
	}

	public Question saveQuestion(Question question) {
		Question question2 = new Question();
		if (!CoreUtil.isEmptyString(question.getSurveyQuestion())) {
			question2.setSurveyQuestion(question.getSurveyQuestion());
		}
		if (!CoreUtil.isEmptyObject(question.getCategory())) {
			question2.setCategory(question.getCategory());
		}
		Category category = new Category();
		question = questionRepository.save(question2);
		Optional<Category> optionalCategory = categoryRepository.findById(question.getCategory().getId());
		if (!optionalCategory.isEmpty()) {
			category = optionalCategory.get();
		}
		category.getQuestions().add(question2);
		categoryRepository.save(category);
		return question;
	}

	public List<Question> getQuestionsWithNpmScore(Long categoryId) {
		List<Question> questions = categoryRepository.findById(categoryId).get().getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			question = calculateNpmScore(question);
			questions.get(i).setNpmScore(question.getNpmScore());
		}
		questions = questionRepository.saveAll(questions);
		return questions;
	}

	private Question calculateNpmScore(Question question) {
		Long negativeScore = 0L;
		Long pozitiveScore = 0L;
		int answerSize = 0;
		Long npmScore = 0L;
		List<Answer> answers = question.getAnswers();
		answerSize = answers.size();
		for (int i = 0; i <answers.size(); i++) {
			Long point = answers.get(i).getPoint().longValue();
			if (point <= 6) {
				negativeScore++;
			} else if (point >= 9 && point <= 10) {
				pozitiveScore++;
			}
		}
		if(pozitiveScore == 0 && negativeScore==0) {
			npmScore = 0L;
		}else {
			npmScore = (pozitiveScore - negativeScore)/answerSize*100;
		}
		question.setNpmScore(npmScore);

		return question;
	}

}
