package com.certification.rocketseat.modules.questions.controller;

import com.certification.rocketseat.modules.questions.dto.AlternativesResultDto;
import com.certification.rocketseat.modules.questions.dto.QuestionResultDto;
import com.certification.rocketseat.modules.questions.entidades.Alternative;
import com.certification.rocketseat.modules.questions.entidades.Question;
import com.certification.rocketseat.modules.questions.repository.QuestionRepository;
import com.certification.rocketseat.modules.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDto> findByTechnology(@PathVariable String technology) {
        var result = this.repository.findByTechnology(technology);

        return result.stream().map(QuestionController::mapQuestionToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestionWithAlternatives(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
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

    public static AlternativesResultDto mapAlternativeDto(Alternative alternativeEntity) {

        if (alternativeEntity == null) {
            throw new IllegalArgumentException("A entrada alternativeEntity não pode ser nula!");
        }

        return AlternativesResultDto.builder()
                .id(alternativeEntity.getId())
                .description(alternativeEntity.getDescription())
                .build();
    }

}
