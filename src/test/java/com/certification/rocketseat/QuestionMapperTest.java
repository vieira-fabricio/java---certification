package com.certification.rocketseat;

import com.certification.rocketseat.modules.questions.controller.QuestionController;
import com.certification.rocketseat.modules.questions.dto.AlternativesResultDto;
import com.certification.rocketseat.modules.questions.dto.QuestionResultDto;
import com.certification.rocketseat.modules.questions.entidades.Alternative;
import com.certification.rocketseat.modules.questions.entidades.Question;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionMapperTest {

    @Test
    public void testMapQuestionToDto() {
        // Arrange
        UUID questionId = UUID.randomUUID();
        UUID alt1Id = UUID.randomUUID();
        UUID alt2Id = UUID.randomUUID();

        Alternative alt1 = Alternative.builder()
                .id(alt1Id)
                .description("Java Virtual Machine")
                .build();

        Alternative alt2 = Alternative.builder()
                .id(alt2Id)
                .description("JavaScript Virtual Machine")
                .build();

        Question question = Question.builder()
                .id(questionId)
                .technology("Java")
                .description("What is JVM?")
                .alternatives(Arrays.asList(alt1, alt2))
                .build();

        // Act
        QuestionResultDto result = QuestionController.mapQuestionToDto(question);

        // Assert
        assertNotNull(result);
        assertEquals(questionId, result.getId());
        assertEquals("Java", result.getTechnology());
        assertEquals("What is JVM?", result.getDescription());
        assertNotNull(result.getAlternativesResultDto());
        assertEquals(2, result.getAlternativesResultDto().size());

        AlternativesResultDto alt1Dto = result.getAlternativesResultDto().get(0);
        assertEquals(alt1Id, alt1Dto.getId());
        assertEquals("Java Virtual Machine", alt1Dto.getDescription());

        AlternativesResultDto alt2Dto = result.getAlternativesResultDto().get(1);
        assertEquals(alt2Id, alt2Dto.getId());
        assertEquals("JavaScript Virtual Machine", alt2Dto.getDescription());
    }
}
