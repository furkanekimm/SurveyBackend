package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>{

}
