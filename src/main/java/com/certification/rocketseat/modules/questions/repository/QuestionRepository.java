package com.certification.rocketseat.modules.questions.repository;

import com.certification.rocketseat.modules.questions.entidades.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findByTechnology(String technology);
}
