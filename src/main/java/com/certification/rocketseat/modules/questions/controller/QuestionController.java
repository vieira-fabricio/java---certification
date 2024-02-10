package com.certification.rocketseat.modules.questions.controller;

import com.certification.rocketseat.modules.questions.dto.AlternativesResultDto;
import com.certification.rocketseat.modules.questions.dto.QuestionResultDto;
import com.certification.rocketseat.modules.questions.entidades.AlternativeEntity;
import com.certification.rocketseat.modules.questions.entidades.QuestionEntity;
import com.certification.rocketseat.modules.questions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository repository;
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDto> findByTechnology(@PathVariable String technology) {
       var result = this.repository.findByTechnology(technology);

        return result.stream().map(QuestionController::mapQuestionToDto)
                .collect(Collectors.toList());
    }

    static QuestionResultDto mapQuestionToDto(QuestionEntity question) {

        if (question == null) {
            throw new IllegalArgumentException("A entrada questão não pode ser nula!");
        }

        List<AlternativesResultDto> alternativesResultDto =
                Optional.ofNullable(question.getAlternativeEntity())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(alternative -> mapAlternativeDto(alternative))
                        .collect(Collectors.toList());

        return QuestionResultDto.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription())
                .alternativesResultDto(alternativesResultDto)
                .build();

    }

    private static AlternativesResultDto mapAlternativeDto(AlternativeEntity alternativeEntity) {

        if (alternativeEntity == null) {
            throw new IllegalArgumentException("A entrada alternativeEntity não pode ser nula!");
        }
        return AlternativesResultDto.builder()
                .id(alternativeEntity.getId())
                .description(alternativeEntity.getDescription())
                .build();
    }
}
