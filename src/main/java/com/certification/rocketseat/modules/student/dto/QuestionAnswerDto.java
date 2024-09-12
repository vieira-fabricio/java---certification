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

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public UUID getAlternativeId() {
        return alternativeId;
    }

    public void setAlternativeId(UUID alternativeId) {
        this.alternativeId = alternativeId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
