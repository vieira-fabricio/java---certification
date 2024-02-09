package com.certification.rocketseat.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDto {

    private UUID questionId;
    private UUID alternativeId;
    private boolean isCorrect;
}
