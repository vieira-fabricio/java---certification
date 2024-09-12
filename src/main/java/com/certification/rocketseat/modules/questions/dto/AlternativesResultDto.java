package com.certification.rocketseat.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlternativesResultDto {

    private UUID id;
    private String description;
    private boolean isCorrect;
    private LocalDateTime createdAt;

}
