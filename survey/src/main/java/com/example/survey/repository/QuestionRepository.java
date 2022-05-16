package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>{

}
