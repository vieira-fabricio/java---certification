package com.certification.rocketseat.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResultDto {

    private UUID id;
    private String technology;
    private String description;

    private List<AlternativesResultDto> alternativesResultDto;

}
