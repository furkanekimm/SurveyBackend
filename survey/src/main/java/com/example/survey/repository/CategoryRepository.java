package com.example.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.survey.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
