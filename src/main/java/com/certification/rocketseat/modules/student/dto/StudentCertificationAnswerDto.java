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
    private String name;
    private List<QuestionAnswerDto> questionAnswerDto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionAnswerDto> getQuestionAnswerDto() {
        return questionAnswerDto;
    }

    public void setQuestionAnswerDto(List<QuestionAnswerDto> questionAnswerDto) {
        this.questionAnswerDto = questionAnswerDto;
    }
}
