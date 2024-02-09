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

import java.util.List;
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
        var questionResultDto = QuestionResultDto.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDto> alternativesResultDto = question
                .getAlternativeEntity()
                .stream().map(QuestionController::mapAlternativeDto)
                .collect(Collectors.toList());

        questionResultDto.setAlternativesResultDto(alternativesResultDto);
        return questionResultDto;
    }

    static AlternativesResultDto mapAlternativeDto(AlternativeEntity alternativeEntity) {
        return AlternativesResultDto.builder()
                .id(alternativeEntity.getId())
                .description(alternativeEntity.getDescription()).build();
    }
}
