package com.certification.rocketseat.modules.questions.service;

import com.certification.rocketseat.modules.questions.controller.QuestionController;
import com.certification.rocketseat.modules.questions.dto.AlternativesResultDto;
import com.certification.rocketseat.modules.questions.dto.QuestionResultDto;
import com.certification.rocketseat.modules.questions.entidades.Alternative;
import com.certification.rocketseat.modules.questions.entidades.Question;
import com.certification.rocketseat.modules.questions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    public Question saveQuestionWithAlternatives(Question question) {
        // Vincula cada alternativa à questão
        question.getAlternatives().forEach(alternative -> alternative.setQuestions(question));

        // Salva a questão, o que automaticamente salva as alternativas por conta do cascade
        return repository.save(question);
    }

    // Métodos de mapeamento já existentes
    public static QuestionResultDto mapQuestionToDto(Question question) {

        if (question == null) {
            throw new IllegalArgumentException("A entrada questão não pode ser nula!");
        }
        List<AlternativesResultDto> alternativesResultDto =
                Optional.ofNullable(question.getAlternatives())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(QuestionController::mapAlternativeDto)
                        .collect(Collectors.toList());

        return QuestionResultDto.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription())
                .alternativesResultDto(alternativesResultDto)
                .build();
    }

    private static AlternativesResultDto mapAlternativeDto(Alternative alternativeEntity) {

        if (alternativeEntity == null) {
            throw new IllegalArgumentException("A entrada alternativeEntity não pode ser nula!");
        }

        return AlternativesResultDto.builder()
                .id(alternativeEntity.getId())
                .description(alternativeEntity.getDescription())
                .build();
    }

}
