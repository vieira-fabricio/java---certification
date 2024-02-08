package com.certification.rocketseat.modules.questions.repository;

import com.certification.rocketseat.modules.questions.entidades.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

    List<QuestionEntity> findByTecnology(String tecnology);
}
