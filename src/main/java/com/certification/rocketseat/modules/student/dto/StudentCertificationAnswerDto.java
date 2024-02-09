package com.certification.rocketseat.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCertificationAnswerDto {

    private String email;
    private String technology;
    private List<QuestionAnswerDto> questionAnswerDto;
}
